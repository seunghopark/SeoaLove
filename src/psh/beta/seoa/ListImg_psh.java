package psh.beta.seoa;

import java.util.concurrent.ExecutionException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


public class ListImg_psh extends Activity {
	private EditText etStart;
	private EditText etView;
	private Button btnSend;
	public String upup;
	public String testkey;
	HttpList uploader;
	HttpList uploader_btn;
	public String sStart;
	public String sView;

	public static final int INDEX = 0;

	String[] imageUrls;
	String[] imageUrlsTxt;

	DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_at);
		/*테스트입니다. 하하하하하하하하*/
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		
		etStart = (EditText) findViewById(R.id.et_start);
		etView = (EditText) findViewById(R.id.et_view);

		btnSend = (Button) findViewById(R.id.btn_sendData);

		sStart = "1";
		sView = "10";

		uploader = new HttpList();
		
		try {
			upup = uploader.execute(sStart, sView).get();
			// imageUrlsTxt =
			System.out.println(imageUrlsTxt);
			test(upup);

		} catch (InterruptedException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}

		// String[][] parsedData = jsonParserList(result);

		btnSend.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				sStart = etStart.getText().toString();
				sView = etView.getText().toString();

				uploader_btn = new HttpList();

				try {

					upup = uploader_btn.execute(sStart, sView).get();
					System.out.println(upup);
					test(upup);

				} catch (InterruptedException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}

			}

		});

	}

	static class ViewHolder {
		ImageView imageView;
		ProgressBar progressBar;
	}

	private void test(String upup) {

		String in_titie = null;
		String in_content = null;
		// String in_imgadd = null;

		String s = upup;

		JSONArray array = null;

		try {
			Object obj = JSONValue.parseWithException(s);
			array = (JSONArray) obj;

		} catch (ParseException pe) {
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}

		int countOfString = array.size();

		System.out.println(countOfString);

		LinearLayout fontViewArea = (LinearLayout) findViewById(R.id.psh_cont);

		for (int indexOfString = 0; indexOfString < countOfString; indexOfString++) {

			JSONObject obj2 = (JSONObject) array.get(indexOfString);

			in_titie = (String) obj2.get("title");

			in_content = (String) obj2.get("stitle");

			// imageUrls[indexOfString] = (String) obj2.get("img");

			// in_imgadd = (String) obj2.get("img");
			
			RelativeLayout contBox = new RelativeLayout(ListImg_psh.this);
			contBox.setBackgroundColor(Color.rgb(0, 255, 0));

			ImageView img = new ImageView(ListImg_psh.this);
			contBox.addView(img);
			
			TextView title = new TextView(ListImg_psh.this);
			title.setText(in_titie);
			contBox.addView(title);

			TextView content = new TextView(ListImg_psh.this);
			content.setText(in_content);
			contBox.addView(content);

			fontViewArea.addView(contBox);
			ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(
					contBox.getLayoutParams());
			margin.setMargins(20, 15, 20, 15);
			contBox.setLayoutParams(new LinearLayout.LayoutParams(margin));
			ImageLoader imageLoader = ImageLoader.getInstance();
			String inImg = "http://www.pshne.com"+(String) obj2.get("img");			
			imageLoader.displayImage(inImg, img, options);

		}

	}
}
