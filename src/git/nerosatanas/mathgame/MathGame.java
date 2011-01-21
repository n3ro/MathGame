package git.nerosatanas.mathgame;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;

public class MathGame extends Activity implements OnClickListener, OnChronometerTickListener{
    
	TextView operTxt;
	Button options[];
	Chronometer chronometer;
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
    	chronometer = (Chronometer) findViewById(R.id.chronometer);
        operTxt = (TextView) findViewById(R.id.operation);
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
				initGame();
			}
		});
    }
    
    /**
     * 
     * Inits the random generator and numbers. Sets the options text.
     * 
     */
    public void initGame(){
        gen = new Random();
        int lvl = 10;
        int l = gen.nextInt(lvl);
        int r = gen.nextInt(lvl); 
        int operation = 0;
        
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
        options[randomPosition[0]].setText(result+"");
        options[randomPosition[1]].setText(result+gen.nextInt(lvl)+"");
        options[randomPosition[2]].setText(result-gen.nextInt(lvl)+"");   
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
    	for(i = 0; i < randomPosition.length; i++){
    		n = gen.nextInt(3);
    		if(!isIn(n)){
    		   randomPosition[i] = n;	
    		}else{
    			n = gen.nextInt(3);
    			randomPosition[i] = n;
    		}
    	}
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


	@Override
	public void onClick(View v) {
		
    	Log.w("*******************game!!!", chronometer.getDrawingTime()+"");
		if(((Button)findViewById(v.getId())).getText().toString() == ""){
			//TODO: You lose!
		}else if(Integer.parseInt(((Button)findViewById(v.getId())).getText().toString()) == result){
			//TODO:You got a point!
			initGame();
		}else if(Integer.parseInt(((Button)findViewById(v.getId())).getText().toString()) != result){
				//TODO:You got a lose!
				initGame();
			}
	}

	@Override
	public void onChronometerTick(Chronometer chronometer) {
//		Log.w("***************AAAAAAAAAAAa",(this.chronometer.getText().toString().equals("00:05"))+"<----");
		if(this.chronometer.getText().toString().equals("01:00")){
			//TODO:Time's up!
			finish();
			
		}
	}
}
