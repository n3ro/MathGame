package git.nerosatanas.mathgame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;

public class MathGame extends Activity implements OnClickListener, OnChronometerTickListener{
    
	TextView operTxt;
	TextView correct, wrong;
	int cTotal, wTotal;
	Button options[];
	Chronometer chronometer;
	int lvl;
	int result;
	/**
	 * Button to generate new values (operation, results...)
	 */
	Button next;
	Random gen;
	ArrayList<Integer> randomPosition = new ArrayList<Integer>();
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initWidgets();
        initGame();
    	chronometer.start();
    	chronometer.setOnChronometerTickListener(this);
    }
   
    
    /**
     * 
     * Inits widgets, set properties and register listeners.
     *  
     */
    public void initWidgets(){
    	cTotal = wTotal = 0;
    	chronometer = (Chronometer) findViewById(R.id.chronometer);
        operTxt = (TextView) findViewById(R.id.operation);
        correct = (TextView) findViewById(R.id.correct);
        wrong = (TextView) findViewById(R.id.wrong);
        options = new Button[3];
        options[0] = (Button) findViewById(R.id.option_one);
        options[1] = (Button) findViewById(R.id.option_two);
        options[2] = (Button) findViewById(R.id.option_three);   
        options[0].setOnClickListener(this);
        options[1].setOnClickListener(this);
        options[2].setOnClickListener(this);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				nextLevel();
			}
		});
        
        //Interactivity
        next.setEnabled(false);
    }
    
    /**
     * 
     * Inits the random generator and numbers. Sets the options text.
     * 
     */
    public void initGame(){
    	this.gen = new Random();
        lvl = 10;
        int l = gen.nextInt(lvl)+1;
        int r = gen.nextInt(lvl)+1; 
        int operation = 0;
        Log.w("**********************tag", l+"<-l");
        initRandsPos(); 
        
        switch (operation) {
		case 0:
			operTxt.setText(l + " + " + r);
			result = l + r;
			break;
		case 1:
			operTxt.setText(l + " - " + r);
			result = l - r;
			break;
		case 2:
			operTxt.setText(l + " * " + r);
			result = l * r;
			break;
		case 3:
			operTxt.setText(l + " / " + r);
			result = l / r;
			break;
		}
        
        //code for checking random positions =)
        /* 
         * options[randomPosition.get(0)].setText("A");
        options[randomPosition.get(1)].setText("B");
        options[randomPosition.get(2)].setText("C");
        */
        options[randomPosition.get(0)].setText(result+"");
        int temp = gen.nextInt(lvl);
        options[randomPosition.get(1)].setText(result+temp+"");
        temp = gen.nextInt(lvl);
        options[randomPosition.get(2)].setText(result-temp+"");
    }
    
    
    /**
     * 
     * Initialize the array of result's positions.
     * 
     */
    public void initRandsPos(){
    	int b;
    	while(randomPosition.size() < 3){
    		b = this.gen.nextInt(3);
    		if(!randomPosition.contains(b)){
    			randomPosition.add(b);
    		}
    	}
    }
    

	public void onClick(View v) {
		if(((Button)findViewById(v.getId())).getText().toString() == ""){
			//TODO: You lose!
		}else if(Integer.parseInt(((Button)findViewById(v.getId())).getText().toString()) == result){
			//TODO:You got a point!
			correct.setText("Correct: " + (++cTotal));
			initGame();
		}else if(Integer.parseInt(((Button)findViewById(v.getId())).getText().toString()) != result){
		    //TODO:You got a lose!
			wrong.setText("Wrong: " + (++wTotal));
			initGame();
			}
	}

	
	public void onChronometerTick(Chronometer chronometer) {
//		Log.w("***************AAAAAAAAAAAa",(this.chronometer.getText().toString().equals("00:05"))+"<----");
		if(this.chronometer.getText().toString().equals("00:05")){
			//TODO:Time's up!
			chronometer.stop();
			options[0].setEnabled(false);
			options[1].setEnabled(false);
			options[2].setEnabled(false);
			next.setEnabled(true);
		}
	}
	
	public void nextLevel(){
		Log.w("***************AAAAAAAAAAAa","Next lvl babe!!!!");
		lvl += 3;
		randomPosition.clear();
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
		options[0].setEnabled(true);
		options[1].setEnabled(true);
		options[2].setEnabled(true);
		next.setEnabled(false);
		initGame();
	}
}


