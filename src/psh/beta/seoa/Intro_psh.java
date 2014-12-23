package psh.beta.seoa;

import psh.beta.seoa.util.SystemUiHider;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class Intro_psh extends Activity {

	Handler h;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.intro_at);
		
		h = new Handler();
		h.postDelayed(irun, 1000);
	}

	Runnable irun = new Runnable() {

		@Override
		public void run() {		
			Intent i = new Intent(Intro_psh.this, ListImg_psh.class);
			i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(i);
			finish();
		}
	};

	public void onBackPreessed() {
		super.onBackPressed();
		h.removeCallbacks(irun);
	}

	@Override
	public void onStart() {
		super.onStart();
		
	}

	@Override
	public void onStop() {
		super.onStop();
		
	}

}
