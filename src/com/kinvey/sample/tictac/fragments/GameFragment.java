package com.kinvey.sample.tictac.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.kinvey.sample.tictac.R;
import com.kinvey.sample.tictac.TicTac;
import com.kinvey.sample.tictac.fragments.EndGameDialog.EndGameDialogListener;

public class GameFragment extends SherlockFragment  {

	public enum WINNER {

		PLAYER("You won!"), COMPUTER("You lost!"), STALEMATE("It's a tie!");

		private String display;

		WINNER(String display) {
			this.display = display;

		}

		public String getDisplay() {
			return this.display;
		}

	}

	

	private static final int n = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSherlockActivity().supportInvalidateOptionsMenu();

		setHasOptionsMenu(true);
	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group,
			Bundle saved) {
		View v = (LinearLayout) inflater.inflate(R.layout.fragment_tic_tac,
				group, false);
		bindViews(v);
		newGame();

		return v;
	}

	private void bindViews(View v) {

	}

	private void newGame() {
	

	}

	

	public void gameOver(WINNER winner) {
		FragmentManager fm = getSherlockActivity().getSupportFragmentManager();
		final EndGameDialog endGameFragment = new EndGameDialog(winner, getSherlockActivity());
		endGameFragment.setListener(new EndGameDialogListener() {

			@Override
			public void onQuit() {
				endGameFragment.dismiss();
				FragmentTransaction ft = getSherlockActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(android.R.id.content, new MenuFragment());
				ft.commit();

			}

			@Override
			public void onNew() {
				newGame();

				endGameFragment.dismiss();
			}
		});
		endGameFragment.show(fm, "fragment_edit_name");

	}

	private void enemyMove() {
	

	}

	private static int[] combineArrays(int[] a, int[] b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		int[] r = new int[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;

	}

	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.fragment_game, menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_newgame:
			newGame();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
