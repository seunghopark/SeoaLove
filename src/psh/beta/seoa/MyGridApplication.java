package psh.beta.seoa;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.content.Context;

public class MyGridApplication {

		public static void configureDefaultImageLoader(Context context){
			// This configuration tuning is custom. You can tune every option, you may tune some of them,
			// or you can create default configuration by
			//  ImageLoaderConfiguration.createDefault(this);
			// method.
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
					.threadPriority(Thread.NORM_PRIORITY - 2)
					.denyCacheImageMultipleSizesInMemory()
					.diskCacheFileNameGenerator(new Md5FileNameGenerator())
					.diskCacheSize(50 * 1024 * 1024) // 50 Mb
					.tasksProcessingOrder(QueueProcessingType.LIFO)
					.writeDebugLogs() // Remove for release app
					.build();
			// Initialize ImageLoader with configuration.
			ImageLoader.getInstance().init(config);
		}
}
