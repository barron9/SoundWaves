package info.bottiger.podcast.receiver;

import info.bottiger.podcast.PodcastBaseFragment;
import info.bottiger.podcast.service.PlayerService;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class HeadsetReceiver extends BroadcastReceiver {

	private static List eventLog;
	private PlayerService mPlayerServiceBinder = PodcastBaseFragment.mPlayerServiceBinder;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
			mPlayerServiceBinder = PodcastBaseFragment.mPlayerServiceBinder;
			if (mPlayerServiceBinder != null) {
				if (mPlayerServiceBinder.isPlaying())
					mPlayerServiceBinder.pause();
			}
		}

		if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
			KeyEvent event = (KeyEvent) intent
					.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			if (KeyEvent.KEYCODE_HEADSETHOOK == event.getKeyCode()) {
				if (KeyEvent.ACTION_DOWN == event.getAction()) {
					if (mPlayerServiceBinder.isPlaying())
						mPlayerServiceBinder.pause();
					else
						mPlayerServiceBinder.start();
				}
			}
		}
	}
}