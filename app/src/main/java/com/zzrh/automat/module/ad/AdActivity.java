package com.zzrh.automat.module.ad;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.zzrh.automat.R;
import com.zzrh.automat.base.BaseActivity;
import com.zzrh.automat.common.Keys;
import com.zzrh.automat.module.homepage.HomePageActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdActivity extends BaseActivity implements AdContract.View {


	@BindView(R.id.vv_ad)
	VideoView mVvAd;
	@BindView(R.id.rl_ad)
	RelativeLayout mRlAd;
	@BindView(R.id.iv_ad)
	ImageView mIvAd;
	private AdContract.Presenter mPresenter;
	private CountDownTimer mTimer = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad);
		ButterKnife.bind(this);
		mPresenter = new AdPresenter(this, this);


	}


	@Override
	public void setPresenter(AdContract.Presenter presenter) {
		this.mPresenter = presenter;
	}

	@Override
	public void showImg(final File[] imgFiles) {
		mVvAd.setVisibility(View.GONE);
		mIvAd.setVisibility(View.VISIBLE);
		mTimer = new CountDownTimer(System.currentTimeMillis(), Keys.Conifg.IMG_XUNHUAN_JIANGE *
				1000) {
			int i = 0;

			@Override
			public void onTick(long millisUntilFinished) {
				Glide.with(AdActivity.this).load(imgFiles[i % imgFiles.length]).into(mIvAd);
				i++;

			}

			@Override
			public void onFinish() {

			}
		};
		mTimer.start();

	}

	@Override
	public void showVideo() {
		mIvAd.setVisibility(View.GONE);
		mVvAd.setVisibility(View.VISIBLE);
		mVvAd.setVideoURI(Uri.parse(getExternalFilesDir(Environment.DIRECTORY_MOVIES).listFiles()[0].getPath()));
		mVvAd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.start();
				mp.setLooping(true);
			}
		});
		mVvAd.start();
	}

	@Override
	public void showDefImg() {
		mVvAd.setVisibility(View.GONE);
		mIvAd.setVisibility(View.VISIBLE);
		Glide.with(AdActivity.this).load(R.drawable.ad_img_def_01).into(mIvAd);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mTimer != null) {
			mTimer.cancel();
		}
		if (mVvAd.isPlaying()) {
			mVvAd.pause();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		mPresenter.loadScreensaverRes();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}


	@Override
	protected void onDestroy() {
		if (mPresenter != null) {
			mPresenter = null;
		}
		super.onDestroy();
		mVvAd.stopPlayback();
	}

	@OnClick(R.id.rl_ad)
	public void onViewClicked() {
		startActivity(new Intent(AdActivity.this, HomePageActivity.class));
	}
}
