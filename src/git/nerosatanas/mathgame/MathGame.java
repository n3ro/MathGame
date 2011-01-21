package git.nerosatanas.mathgame;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MathGame extends Activity {
    
	TextView operTxt;
	Button options[];
	Random gen;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initWidgets();
        initRands();

    }
    
    public void initWidgets(){
        operTxt = (TextView) findViewById(R.id.operation);
        options = new Button[3];
        options[0] = (Button) findViewById(R.id.option_one);
        options[1] = (Button) findViewById(R.id.option_two);
        options[2] = (Button) findViewById(R.id.option_three);    	
    }
    
    public void initRands(){
        gen = new Random();
        int l = gen.nextInt(10);
        int r = gen.nextInt(10); 
        int mislead = gen.nextInt(10);

        operTxt.setText(l + " + " + r);
        options[0].setText(l+r+"");
        options[1].setText(l+r+mislead+"");
        options[2].setText(l+r-mislead+"");
        
    }
}
