package edu.fundup.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import edu.fundup.model.entity.PostAnimalSettings;
import edu.fundup.model.iservice.IServiceComment;
import edu.fundup.model.iservice.IServiceSettings;
import edu.fundup.model.iservice.IServiceSms;
import edu.fundup.model.iservice.IServiceanimeaux;
import edu.fundup.model.service.ServiceAnimeauxImpl;
import edu.fundup.model.service.ServiceCommentsImpl;
import edu.fundup.model.service.ServiceSettingsImpl;
import edu.fundup.model.service.TwilioService;

/**
*
* @author Jsaoudi
*/


public class FuFactory {
	private static Set<Class> availableDrivers = new HashSet<>();

	// animalDriver
	private static IServiceanimeaux animalDriver = new ServiceAnimeauxImpl();
	// smsDriver
	private static IServiceSms smsDriver = new TwilioService();

	// SettingDriver
	private static IServiceSettings settingDriver = new ServiceSettingsImpl();

	// SupportDriver
		//private static IServiceSupport supportDriver = new DefaultResourceLoader();
	
	// SupportDriver
		private static IServiceComment commentDriver = new ServiceCommentsImpl();
		
	
	
	
	
	
	public static IServiceComment getCommentDriver() {
		return FuFactoryGetter.get(IServiceComment.class, commentDriver);
		}



	public static IServiceanimeaux getAnimalDriver() {
		return FuFactoryGetter.get(IServiceanimeaux.class, animalDriver);
	}






	public static IServiceSms getSmsDriver() {
		return FuFactoryGetter.get(IServiceSms.class, smsDriver);
	}






	public static IServiceSettings getSettingDriver() {
		return FuFactoryGetter.get(IServiceSettings.class, settingDriver);
	}

//
//
//
//
//
//	public static IServiceSupport getSupportDriver() {
//		return FuFactoryGetter.get(IServiceAnimal.class, supportDriver);
//	}






	private static class FuFactoryGetter {

		private static volatile Map<Class, Object> drivers = new ConcurrentHashMap<>();

		public static <T extends Object> T get(Class<T> type, T defaultDriver) {
				T driver = (T) drivers.get(type);
				if (driver == null) {
					synchronized (FuFactoryGetter.class) {
						if (!drivers.containsKey(type)) {
								if (driver != null) {
									drivers.put(type, driver);
								} 
								 else {
									return defaultDriver;
								}
							}
						}
					}
		            return driver!=null?driver:defaultDriver;
	}
}
}
		




