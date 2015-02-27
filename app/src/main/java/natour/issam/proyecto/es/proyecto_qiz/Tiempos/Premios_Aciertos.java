package natour.issam.proyecto.es.proyecto_qiz.Tiempos;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import natour.issam.proyecto.es.proyecto_qiz.R;

public class Premios_Aciertos extends ActionBarActivity {
TextView textopremio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premios__aciertos);

        ParseUser currentUser = ParseUser.getCurrentUser();

        Bundle b = getIntent().getExtras();
        int  tiempousao = b.getInt("tiempousado");
        boolean isconsejousado = b.getBoolean("isconsejoused");
        boolean isfiftyfiftyused = b.getBoolean("isfiftyfiftyused");
        boolean istimeused = b.getBoolean("istimeused");

        TextView textousadopremios = (TextView) findViewById(R.id.textotiempousadopremios);
        textousadopremios.setText(String.valueOf(tiempousao));
        TextView textoistimeusedpremios = (TextView) findViewById(R.id.textoistimeusedpremios);
        textoistimeusedpremios.setText(String.valueOf(istimeused));
        TextView textofiftyusedpremios = (TextView) findViewById(R.id.textofiftyusedpremios);
        textofiftyusedpremios.setText(String.valueOf(isfiftyfiftyused));
        TextView textoconsejos = (TextView) findViewById(R.id.textoconsejoisusadopremios);
        textoconsejos.setText(String.valueOf(isconsejousado));

        textopremio = (TextView) findViewById(R.id.textoPremio);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_premios__aciertos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void premiosYprobabilidad(View view){
        RandomCollection randomCollection = new RandomCollection();
        randomCollection.add(15,"Nada");
        randomCollection.add(7,"X monedas ");
        randomCollection.add(2,"1 Diamante ");
        randomCollection.add(5,"X Puntos de exp ");
        randomCollection.add(20,"1 vida extra!");
        randomCollection.add(25,"1 consejo extra!");
        randomCollection.add(25,"1 ayuda fiftyfifty!");
        randomCollection.add(1,"1 saltar pregunta!");
        String resultado = String.valueOf(randomCollection.next());

        textopremio.setText("Has ganado: "+resultado);

    }

    public class RandomCollection<E> {
        private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
        private final Random random;
        private double total = 0;

        public RandomCollection() {
            this(new Random());
        }

        public RandomCollection(Random random) {
            this.random = random;
        }

        public void add(double weight, E result) {
            if (weight <= 0) return;
            total += weight;
            map.put(total, result);
        }

        public E next() {
            double value = random.nextDouble() * total;
            return map.ceilingEntry(value).getValue();
        }
    }

}
