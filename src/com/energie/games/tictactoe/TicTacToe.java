package com.energie.games.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TicTacToe extends Activity {
	static final int DIALOG_START = 0;
	static final int DIALOG_WIN = 1;
	static final int DIALOG_NOWIN = 2;

	ImageView field[] = new ImageView[9];
    Resources res;
    Drawable PlayerImg[] = new Drawable[3];
    Drawable WinImg[] = new Drawable[2];
    int Player = 1;
    boolean won = false;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        showDialog(DIALOG_START);
        res = getResources();
        PlayerImg[0] = res.getDrawable(R.drawable.tictactoe_blank);
        PlayerImg[1] = res.getDrawable(R.drawable.tictactoe_cross);
        PlayerImg[2] = res.getDrawable(R.drawable.tictactoe_circle);
        WinImg[0] = res.getDrawable(R.drawable.tictactoe_cross_win);
        WinImg[1] = res.getDrawable(R.drawable.tictactoe_circle_win);
        field[0] = (ImageView) findViewById(R.id.block11);
        field[1] = (ImageView) findViewById(R.id.block12);
        field[2] = (ImageView) findViewById(R.id.block13);
        field[3] = (ImageView) findViewById(R.id.block21);
        field[4] = (ImageView) findViewById(R.id.block22);
        field[5] = (ImageView) findViewById(R.id.block23);
        field[6] = (ImageView) findViewById(R.id.block31);
        field[7] = (ImageView) findViewById(R.id.block32);
        field[8] = (ImageView) findViewById(R.id.block33);
        
        field[0].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[0]);			        	
            }
        });
        field[1].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[1]);			        	
            }
        });
        field[2].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[2]);			        	
            }
        });
        field[3].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[3]);			        	
            }
        });
        field[4].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[4]);			        	
            }
        });
        field[5].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[5]);			        	
            }
        });
        field[6].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[6]);			        	
            }
        });
         field[7].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[7]);			        	
            }
        });
        field[8].setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	setImage(field[8]);			        	
            }
        });
    }
	
	public void setImage(ImageView img) {
		Drawable oldImage = img.getDrawable();
		if(oldImage!=PlayerImg[0]&&oldImage!=PlayerImg[1]&&oldImage!=PlayerImg[2])
			oldImage=PlayerImg[0];
		if(oldImage==PlayerImg[0]) {
			img.setImageDrawable(PlayerImg[Player]);
			checkWin(Player);
			if(Player==1)
				Player=2;
			else
				Player=1;
		}
	}
	
	public void restart() {
		won = false;
		Player = 1;
		for(int i = 0; i<field.length; i++) {
			field[i].setImageDrawable(PlayerImg[0]);
		}
	}
	
	protected void onPrepareDialog (int id, Dialog dialog, Bundle args) {
		switch(id)
		{
			case DIALOG_WIN:
				if(!won) {
					dialog=null;
					break;
				}
				TextView player = (TextView) dialog.findViewById(R.id.player);
				if(Player==1)
					player.setText(R.string.p1wl);
				else
					player.setText(R.string.p2wl);
		}
	}
	
	protected Dialog onCreateDialog(int id)
	{
		Dialog dialog;
		switch(id)
		{
			case DIALOG_START:
				Dialog mydialog = new Dialog(TicTacToe.this);

				mydialog.setContentView(R.layout.dialog_start);
				mydialog.setCancelable(false);
				mydialog.setTitle("");

				Button start = (Button) mydialog.findViewById(R.id.start);
				start.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		            	dismissDialog(DIALOG_START);
		            }
		        });
				
				dialog=mydialog;
				break;
			case DIALOG_WIN:
				if(!won) {
					dialog = null;
					break;
				}
				Dialog windialog = new Dialog(TicTacToe.this);

				windialog.setContentView(R.layout.dialog_win);
				windialog.setCancelable(true);
				windialog.setTitle("");
								
				Button restart = (Button) windialog.findViewById(R.id.restart);
				restart.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		            	restart();
		            	dismissDialog(DIALOG_WIN);
		            }
		        });
				Button exit = (Button) windialog.findViewById(R.id.exit);
				exit.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		            	finish();
		            }
		        });

				dialog=windialog;
				break;
			case DIALOG_NOWIN:
				Dialog nowindialog = new Dialog(TicTacToe.this);

				nowindialog.setContentView(R.layout.dialog_nowin);
				nowindialog.setCancelable(true);
				nowindialog.setTitle("");
								
				Button norestart = (Button) nowindialog.findViewById(R.id.restart);
				norestart.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		            	dismissDialog(DIALOG_NOWIN);
		            	restart();
		            }
		        });
				Button noexit = (Button) nowindialog.findViewById(R.id.exit);
				noexit.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		            	finish();
		            }
		        });

				dialog=nowindialog;
				break;
		    default:
				dialog = null;
		}
		return dialog;
	}
	
	public void Win(int Player) {
		won = true;
		this.Player = Player;
		showDialog(DIALOG_WIN);
	}

	public void checkWin(int checkPlayer) {
		//Links->Rechts Reihe 1?
		if(field[0].getDrawable()==PlayerImg[checkPlayer]&&field[1].getDrawable()==PlayerImg[checkPlayer]&&field[2].getDrawable()==PlayerImg[checkPlayer])
		{
			field[0].setImageDrawable(WinImg[checkPlayer-1]);
			field[1].setImageDrawable(WinImg[checkPlayer-1]);
			field[2].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
		//Links->Rechts Reihe 2?
		else if(field[3].getDrawable()==PlayerImg[checkPlayer]&&field[4].getDrawable()==PlayerImg[checkPlayer]&&field[5].getDrawable()==PlayerImg[checkPlayer])
		{
			field[3].setImageDrawable(WinImg[checkPlayer-1]);
			field[4].setImageDrawable(WinImg[checkPlayer-1]);
			field[5].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
		//Links->Rechts Reihe 3?
		else if(field[6].getDrawable()==PlayerImg[checkPlayer]&&field[7].getDrawable()==PlayerImg[checkPlayer]&&field[8].getDrawable()==PlayerImg[checkPlayer])
		{
			field[6].setImageDrawable(WinImg[checkPlayer-1]);
			field[7].setImageDrawable(WinImg[checkPlayer-1]);
			field[8].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
		//Oben->Unten Reihe 1?
		else if(field[0].getDrawable()==PlayerImg[checkPlayer]&&field[3].getDrawable()==PlayerImg[checkPlayer]&&field[6].getDrawable()==PlayerImg[checkPlayer])
		{
			field[0].setImageDrawable(WinImg[checkPlayer-1]);
			field[3].setImageDrawable(WinImg[checkPlayer-1]);
			field[6].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
		//Oben->Unten Reihe 2?
		else if(field[1].getDrawable()==PlayerImg[checkPlayer]&&field[4].getDrawable()==PlayerImg[checkPlayer]&&field[7].getDrawable()==PlayerImg[checkPlayer])
		{
			field[1].setImageDrawable(WinImg[checkPlayer-1]);
			field[4].setImageDrawable(WinImg[checkPlayer-1]);
			field[7].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
		//Oben->Unten Reihe 3?
		else if(field[2].getDrawable()==PlayerImg[checkPlayer]&&field[5].getDrawable()==PlayerImg[checkPlayer]&&field[8].getDrawable()==PlayerImg[checkPlayer])
		{
			field[2].setImageDrawable(WinImg[checkPlayer-1]);
			field[5].setImageDrawable(WinImg[checkPlayer-1]);
			field[8].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
		//LinksOben->RechtsUnten?
		else if(field[0].getDrawable()==PlayerImg[checkPlayer]&&field[4].getDrawable()==PlayerImg[checkPlayer]&&field[8].getDrawable()==PlayerImg[checkPlayer])
		{
			field[0].setImageDrawable(WinImg[checkPlayer-1]);
			field[4].setImageDrawable(WinImg[checkPlayer-1]);
			field[8].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
		//RechtsOben->LinksUnten?
		else if(field[2].getDrawable()==PlayerImg[checkPlayer]&&field[4].getDrawable()==PlayerImg[checkPlayer]&&field[6].getDrawable()==PlayerImg[checkPlayer])
		{
			field[2].setImageDrawable(WinImg[checkPlayer-1]);
			field[4].setImageDrawable(WinImg[checkPlayer-1]);
			field[6].setImageDrawable(WinImg[checkPlayer-1]);
			Win(checkPlayer);
		}
//		else
//			Log.d("NoWin", "No Win");
		boolean nowin = true;
		for(int i = 0; i<field.length; i++) {
			Drawable Image = field[i].getDrawable();
			if(Image!=PlayerImg[0]&&Image!=PlayerImg[1]&&Image!=PlayerImg[2])
				Image=PlayerImg[0];
			
			if(Image==PlayerImg[0]) {
				nowin = false;
				continue;
			}
		}
		if(nowin)
			showDialog(DIALOG_NOWIN);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        try {
       		inflater.inflate(R.menu.main, menu);
        } catch(Exception e) {
        	Log.e("Menu", "Fehler: "+e.toString());
        }
         return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
    	switch (item.getItemId()) {
	        case R.id.restart:
	        	restart();
	        	return true;
	        case R.id.exit:
	        	finish();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
    	}
    }

}