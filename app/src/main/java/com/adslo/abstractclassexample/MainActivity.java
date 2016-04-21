package com.adslo.abstractclassexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstMain = null;
    ArrayList lst    =  new <String>ArrayList();

    ArrayAdapter adapter = null;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListView();
        doActionEvent();
    }

    // ListView에 추가
    public void setListView(){
        lstMain = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lst);
        lstMain.setAdapter(adapter);

        lstMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // click 했을 때 해당 항목을 삭제 해보자..
                // removeItem(position); <-- 메소드를 만들어 규현해보자.
                Toast.makeText(getApplicationContext(), (String)lst.get(position), Toast.LENGTH_LONG ).show();
            }
        });

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
            }
        });
    }

    private void clearList() {
        lst.clear();
        adapter.notifyDataSetChanged();
    }

    // ListView에 add Item
    public void addListItem(String sMessage){
        lst.add(sMessage);
        adapter.notifyDataSetChanged();
    }

    // Gmnae이 진행되고 있는 상황....이벤트가 발생
    public void doActionEvent(){
        Player p  = new Paladin();
        Player p2 = new WitchDoctor();

        p.attck();
        p2.defend();

        p2.attck();
        p.defend();

        p.drinkRedBottle();
        p2.drinkRedBottle();
    }

    // inner class

    /*
    *
    *      일반개발자들이 사용하기 편하게 시스템에서 구현한 클래스
    *
    * */
    // 게임에서 필요한 Player
    abstract class Player{
        public void shout(String sMessage){
            String sIam = this.getClass().getName();
            sIam = sIam.replace(getPackageName() + ".MainActivity$", "");

            addListItem(sIam + ":>" + sMessage);
        }

        // 상속받은 클래스들이 알아서 구현해라.
        abstract void attck();
        abstract void defend();
        abstract void drinkRedBottle();
    }

    /*
    *
    *      여기서부터 일반 개발자들이 사용하는 코드
    *
    * */
    class Paladin extends Player{
        @Override
        void attck() {
            shout("[공격] 빛의 축복으로 빛나는 나의 철퇴를 받아라!! 악마들아!");
        }

        @Override
        void defend() {
            shout("[방어] 신의 축복으로 너의 공격은 무의미 해질 것이다");
        }

        @Override
        void drinkRedBottle() {
            shout("[마시자] 솟아라! 힘...");
        }
    }

    class WitchDoctor extends Player{
        @Override
        void attck() {
            shout("[공격]저주를 받아라..!#@$%$@%^%^%");
        }

        @Override
        void defend() {
            shout("[방어]어둠의 아우라여 나를 보호하라.. @#@!!!!");
        }

        @Override
        void drinkRedBottle() {
            shout("[마시자]쿠에에억~");
        }
    }
}
