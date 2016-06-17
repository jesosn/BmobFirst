package com.example.administrator.bmobfirst;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;

public class NoteListActivity extends AppCompatActivity {
    private static final int DEL_ITEM = 0x1;
    private List<Note> notes = new ArrayList<>();
    private ListView listView_note;
    NoteAdapter na;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        listView_note = (ListView) findViewById(R.id.listView_note);
        Bmob.initialize(this, "2c7868d703be32aadae7fcfa39821c5c");


        listView_note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView_context = (TextView) view.findViewById(R.id.textView_content);
                String content = textView_context.getText().toString();
                String objectid = (String) view.getTag();
                Intent intent = new Intent(NoteListActivity.this,NoteDetailActivity.class);
                intent.putExtra("content",content);
                intent.putExtra("objectid",objectid);
                startActivity(intent);
            }
        });

        registerForContextMenu(listView_note);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, DEL_ITEM, 100, "删除");//groupID,itemID,order,char
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case DEL_ITEM:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("删除确认");
                builder.setMessage("真的要删除么？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //convertView中有objectID
                        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        View view = info.targetView;
                        String objectid = (String) view.getTag();
                        //这里执行删除的方法
                        Note note = new Note();
                        note.delete(NoteListActivity.this, objectid, new DeleteListener() {
                            @Override
                            public void onSuccess() {
                                loadData();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(NoteListActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //啥也不用干
                    }
                });
                builder.setCancelable(false);
                builder.show();

                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();

    }

    private void loadData() {
        //目的是从后台取出数据
        BmobQuery<Note> query = new BmobQuery<Note>();
//查询playerName叫“比目”的数据
//        query.addWhereEqualTo("playerName", "比目");
//返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
//执行查询方法
        query.findObjects(this, new FindListener<Note>() {
            @Override
            public void onSuccess(List<Note> list) {
                notes = list;
                na = new NoteAdapter(NoteListActivity.this,notes);
                listView_note.setAdapter(na);
                // TODO Auto-generated method stub
//                toast("查询成功：共"+object.size()+"条数据。");
//                for (Note note : list) {
                    //获得playerName的信息
//                    note.getContent();
                    //获得数据的objectId信息
//                    gameScore.getObjectId();
                    //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
//                    gameScore.getCreatedAt();
//                }
            }
            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
//                toast("查询失败："+msg);
                Toast.makeText(NoteListActivity.this,"查询失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class NoteAdapter extends BaseAdapter {
        private Context context;
        private List<Note> list;

        public NoteAdapter(Context context, List<Note> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_note_item,null);
            }
            Note note = list.get(position);

            TextView content = (TextView) convertView.findViewById(R.id.textView_content);
            content.setText(note.getContent());
            convertView.setTag(note.getObjectId());

            return convertView;
            //ViewHolder的优化就先不写了
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_list,menu);
//        return super.onCreateOptionsMenu(menu);????
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note:
                Intent intent = new Intent(NoteListActivity.this,NewNote.class);
                startActivity(intent);
                break;
        }


//        return super.onOptionsItemSelected(item);
        return true;
    }
}
