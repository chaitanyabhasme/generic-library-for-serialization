package genericCheckpointing.util;

import genericCheckpointing.server.StoreRestoreI;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Proxycreator class to create a proxy instance
 * @author Chaitanya
 *
 */
public class ProxyCreator{

	/**
	 * this method returns creates a proxy instance for the required input parameters
	 * @param interfaceArray the array of known interfaces
	 * @param handler invocationaHandler object required to create the proxy
	 * @return generated proxy
	 */
	public StoreRestoreI createProxy(Class<?>[] interfaceArray, InvocationHandler handler){
		StoreRestoreI storeRestoreRef =
            (StoreRestoreI)
            Proxy.newProxyInstance(
                                   getClass().getClassLoader(),
                                   interfaceArray,
                                   handler
                                   );

		return storeRestoreRef;
	}
}
