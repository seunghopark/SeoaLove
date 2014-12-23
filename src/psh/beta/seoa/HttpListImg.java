package psh.beta.seoa;

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

import android.os.AsyncTask;
import android.util.Log;

//Uploader class
public class HttpListImg extends AsyncTask<String, Void, String> {	
	
	protected String doInBackground(String... param) {
		String outPut = null;
		
		if(param[0] == null)
			param[0] = "";
		if(param[1] == null)
			param[1] = "";
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("list_start", param[0]));
		nameValuePairs.add(new BasicNameValuePair("list_ea", param[1]));
		
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// HttpPost httppost = new
			// HttpPost("http://app.zzan.me/project_hooit/profile/profile_img_register.php");

			HttpPost httppost = new HttpPost("http://www.pshne.com/pshne_travel/jeju_andjson.php");

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity entity = response.getEntity();

			// print responce

			outPut = EntityUtils.toString(entity);

			Log.i("GET RESPONSE—-", outPut);

			// is = entity.getContent();
			Log.e("log_tag ******", "good connection");

			

		} catch (Exception e) {
			Log.e("log_tag ******", "Error in http connection " + e.toString());
		}

		return outPut;
	}
}