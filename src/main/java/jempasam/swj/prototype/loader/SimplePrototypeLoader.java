package jempasam.swj.prototype.loader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import jempasam.swj.data.chunk.DataChunk;
import jempasam.swj.data.chunk.ObjectChunk;
import jempasam.swj.data.chunk.ValueChunk;
import jempasam.swj.data.deserializer.DataDeserializer;
import jempasam.swj.deserializer.PrimitiveDeserializer;
import jempasam.swj.logger.SLogger;
import jempasam.swj.prototype.Prototype;
import jempasam.swj.prototype.PrototypeManager;
import jempasam.swj.prototype.loader.tags.ObjectParameter;
import jempasam.swj.prototype.loader.tags.ObjectSerializable;

public class SimplePrototypeLoader<T extends Prototype> extends PrototypeLoader<T>{
	
	protected Class<T> clazz;
	protected String prefix;
	protected PrimitiveDeserializer primDeserializer;
	
	
	public SimplePrototypeLoader(PrototypeManager<T> manager, SLogger logger, DataDeserializer deserializer, PrimitiveDeserializer primDeserializer, Class<T> clazz, String prefix) {
		super(manager, logger, deserializer);
		this.clazz=clazz;
		this.prefix=prefix;
		this.primDeserializer=primDeserializer;
	}

	
	@Override
	protected void interpret(ObjectChunk data) {
		for(DataChunk d : data) {
			logger.enter(data.getName());
			if(d instanceof ObjectChunk) {
				Prototype o=(Prototype)createObject((ObjectChunk)d, clazz);
				if(o!=null) {
					manager.register(d.getName(), (T)o);
					logger.log("RESULT: registred");
				}
				else logger.log("RESULT: Ignored");
			}
			logger.exit();
		}
	}
	
	private static class InsideException extends Exception{
		public InsideException(String message) { super(message); }
	}
	private interface ThrowingConsumer<T>{
		void accept(T t) throws Exception;
	}
	private Object createObject(ObjectChunk data, Class<?> rootclass) {
		
		DataChunk classchunk=null;
		String classname="";
		Class<?> objectclass=null;
		Object newobject=null;
		ClassLoader loader=null;
		
		logger.enter(data.getName());
		try {
			loader=getClass().getClassLoader();
			
			//Get the object class name
			classchunk=data.get("type");
			if(classchunk==null || !(classchunk instanceof ValueChunk)) throw new InsideException("Miss the parameter \"type\".");
			classname=prefix+((ValueChunk)classchunk).getValue();
			
			//Get the object class
			objectclass=loader.loadClass(classname);
			if(objectclass==null || !objectclass.isAnnotationPresent(ObjectSerializable.class))
				throw new ClassNotFoundException();
			if(!rootclass.isAssignableFrom(objectclass))throw new InsideException("This type cannot be used there.");
			
			
			//Instantiate the object
			newobject=objectclass.getConstructor().newInstance();
			
			//Load parameters
			for(DataChunk d : data) {
				if(!d.getName().equals("type")) {
					logger.enter("parameter \""+d.getName()+"\"");
					try {
						Class<?> parameter_type=null;
						Object value_setter_target=newobject;
						ThrowingConsumer<Object> value_setter=null;
						
						//Get parameter setter and type
						try {
							//Get field
							Field field=objectclass.getField(d.getName());
							if(field==null || !field.isAnnotationPresent(ObjectParameter.class)) throw new NoSuchFieldException();
							parameter_type=field.getType();
							value_setter=(o)->field.set(value_setter_target, o);
						}catch(NoSuchFieldException e) {
							//Get method
							Method method=null;
							for(Method m : objectclass.getMethods()) {
								if(m.getParameterCount()==1 && m.isAnnotationPresent(ObjectParameter.class) && m.getName().equals(d.getName())) {
									method=m;
									break;
								}
							}
							if(method==null) throw new NoSuchMethodException();
							parameter_type=method.getParameterTypes()[0];
							Method setter_method=method;
							value_setter=(o)->setter_method.invoke(value_setter_target, o);
						}
						
						//Set field value
						if(d instanceof ValueChunk) {
							//If is primitive
							if(!parameter_type.isPrimitive() && parameter_type!=String.class){
								ValueChunk oc=(ValueChunk)d;
								Object loadedobj=manager.get(oc.getValue());
								if(loadedobj==null)throw new InsideException("The object \""+loadedobj+"\" does not exist");
								if(!parameter_type.isAssignableFrom(loadedobj.getClass()))throw new InsideException("The object \""+loadedobj+"\" is not of the right type.");
								value_setter.accept(loadedobj);
								logger.log("Parameter registred as \""+oc.getName()+"\"=Object named \""+oc.getValue()+"\"");
							}else {
								ValueChunk oc=(ValueChunk)d;
								value_setter.accept(primDeserializer.asType(parameter_type, oc.getValue()));
								logger.log("Parameter registred as \""+oc.getName()+"\"=\""+oc.getValue()+"\"");
							}
						}else if(d instanceof ObjectChunk){
							//If is object
							if(parameter_type.isPrimitive() || parameter_type==String.class)
								throw new InsideException("This parameter should be primitive not an object.");
							Object obj=createObject((ObjectChunk)d, parameter_type);
							if(obj==null)throw new InsideException("Invalid object parameter");
							value_setter.accept(obj);
						}
						
					}catch(NoSuchMethodException e){
						logger.log("This parameter does not exist");
					}catch (NumberFormatException e) {
						logger.log("Primitive parameter value is malformed or not of the good type");
					}catch (InsideException e) {
						logger.log(e.getMessage());
					}catch(Exception e) {
						logger.log("Unexpexted error of type \""+e.getClass().getName()+"\"");
					}
					logger.exit();
				}
			}
		} catch (ClassNotFoundException e) {
			logger.log("The type \""+classname+"\" does not exist");
		} catch(NoSuchMethodException | InstantiationException e) {
			logger.log("The type \""+classname+"\" is not instantiable");
		}catch (InsideException e) {
			logger.log(e.getMessage());
		}catch (Exception e) {
			logger.log("Unexpexted error of type \""+e.getClass().getName()+"\"");
		}
		
		logger.exit();
		return newobject;
	}

}
