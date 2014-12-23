package psh.beta.seoa;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

//Uploader class
public class HttpUploader extends AsyncTask<String, Void, String> {

	protected String doInBackground(String... param) {
		String outPut = null;

		Bitmap bitmapOrg = BitmapFactory.decodeFile(param[2]);
		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		// Resize the image
		double width = bitmapOrg.getWidth();
		double height = bitmapOrg.getHeight();
		double ratio = 400 / width;
		int newheight = (int) (ratio * height);

		System.out.println("———-width" + width);
		System.out.println("———-height" + height);

		bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 400, newheight, true);

		// Here you can define .PNG as well
		bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 95, bao);
		byte[] ba = bao.toByteArray();
		String ba1 = Base64.encodeToString(ba, 0);

		System.out.println("uploading image now" + ba1);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("image", ba1));
		nameValuePairs.add(new BasicNameValuePair("user", param[0]));
		nameValuePairs.add(new BasicNameValuePair("file", param[1]));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			// HttpPost httppost = new
			// HttpPost("http://app.zzan.me/project_hooit/profile/profile_img_register.php");

			HttpPost httppost = new HttpPost(
					"http://app.zzan.me/html/upload.php");

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity entity = response.getEntity();

			// print responce

			outPut = EntityUtils.toString(entity);

			Log.i("GET RESPONSE—-", outPut);

			// is = entity.getContent();
			Log.e("log_tag ******", "good connection");

			bitmapOrg.recycle();

		} catch (Exception e) {
			Log.e("log_tag ******", "Error in http connection " + e.toString());
		}

		return outPut;
	}
}