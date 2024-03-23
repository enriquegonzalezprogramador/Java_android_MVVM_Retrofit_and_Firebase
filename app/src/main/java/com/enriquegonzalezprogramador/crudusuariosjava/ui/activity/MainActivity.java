package com.enriquegonzalezprogramador.crudusuariosjava.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.enriquegonzalezprogramador.crudusuariosjava.R;
import com.enriquegonzalezprogramador.crudusuariosjava.ui.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializeToothpick();
        setContentView(R.layout.activity_main);

        // Cargar el ListFragment en el contenedor principal
        loadListFragment();
    }

   /* private void initializeToothpick() {
        Scope scope = Toothpick.openScope(this);
        scope.installModules(new AppModule(this.getApplication()));

    }*/

    private void loadListFragment() {
        // Obtener el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Iniciar una transacción de fragmentos
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Reemplazar el contenido del contenedor principal con ListFragment
        fragmentTransaction.replace(R.id.fragment_container, new ListFragment());

        // Hacer commit de la transacción
        fragmentTransaction.commit();
    }
}
