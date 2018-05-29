package id.cathode.youtubeplayer;

import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "YOUR_API_KEY";
    static final String YOUTUBE_VIDEO_ID = "ffxKSjUwKdU";
    static final String YOUTUBE_PLAYLIST = "PLx0sYbCqOb8TBPRdmBHs5Iftvv9TPboYG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
//        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is  " + provider.getClass().toString());
        Toast.makeText(this, "Initialized Youtube Player sucessfully", Toast.LENGTH_LONG).toString();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if (!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this, "Good, Video is playing ok!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "Video has paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this, "Video has stopped", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "Click Ad now, Let's make the creator rich!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this, "The video has started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this, "Congrats You have completed the video", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
