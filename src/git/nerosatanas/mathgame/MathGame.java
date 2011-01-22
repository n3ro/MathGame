package git.nerosatanas.mathgame;

import java.util.Random;

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
	int randomPosition[];
	
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
    	this.gen = new Random(912313286L);
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
        
        options[randomPosition[0]].setText("A");
        options[randomPosition[1]].setText("B");
        options[randomPosition[2]].setText("C");
        /*options[randomPosition[0]].setText(result+"");
        int temp = gen.nextInt(lvl);
        options[randomPosition[1]].setText(result+temp+"");
        temp = gen.nextInt(lvl);
        options[randomPosition[2]].setText(result-temp+"");
        */   
    }
    
    
    /**
     * 
     * Initialize the array of result's positions.
     * 
     */
    public void initRandsPos(){
    	randomPosition = new int[3];
    	int n;
    	int i;
    	
    	n = this.gen.nextInt(3);
    	Log.w("**********************tag", n+"<-random");
    	
    	
    	if(n == 2){
    	  randomPosition[0] = n;
    	  randomPosition[1] = n-1;
    	  randomPosition[2] = n+2;
    	}
    	if(n == 1){
      	  randomPosition[0] = n-1;
      	  randomPosition[2] = n+1;
      	  randomPosition[1] = n;
      	}
    	if(n == 0){
        	  randomPosition[0] = n;
        	  randomPosition[2] = n+1;
        	  randomPosition[1] = n+2;
        }
    	
    	Log.w("**********************tag", randomPosition[0]+"");
    	Log.w("**********************tag", randomPosition[1]+"");
    	Log.w("**********************tag", randomPosition[2]+"");
        /*for(i = 0; i < randomPosition.length; i++){
    		n = gen.nextInt(3);
    		
    		while(!isIn(n)){
        		randomPosition[i] = n;
    		}
    		/*Log.w("********************+N: ", n+"");
    		if(!isIn(n)){
    		   randomPosition[i] = n;	
    		}else{
    			n = gen.nextInt(3);
    			Log.w("********************+N: ESTABA!!! NUEVO ", n+"");
    			if(!isIn(n))randomPosition[i] = n;
    			else
    				randomPosition[i] = gen.nextInt(3);
    		}
    	}*/
    }
    
    /**
     * 
     * Check is a number is already in the positions array.
     * 
     * @param number
     * @return boolean
     */
    
    boolean isIn(int number){
    	for(int i = 0; i < randomPosition.length; i++){
    		if(number == randomPosition[i])
    			return true;
    	}
    	return false;
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
		randomPosition = null;
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
		options[0].setEnabled(true);
		options[1].setEnabled(true);
		options[2].setEnabled(true);
		next.setEnabled(false);
		initGame();
	}
}

