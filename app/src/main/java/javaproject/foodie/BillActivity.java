package javaproject.foodie;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
/**
 * Created by ASUS on 02-Nov-17.
 */

public class BillActivity extends Fragment {

    SearchView sc;
    boolean flag;
    String searchtext;
    String itemName[];
    ListItem2 listItem;
    ArrayList<ListItem2> itemArrayList;
    int productidlist[];
    String itemPrice[];
    int itemQuantity[];
    int count;
    ConnectionClass connectionClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_list_item,container,false);
        final ListView listView2 = (ListView) view.findViewById(R.id.content_list_item_list_view);
        //sc = (SearchView)view.findViewById(R.id.sc);
        //MainActivity.f_no=3;
        final CustomeAdapter2 customeAdapter ;
        connectionClass = new ConnectionClass();
        final Connection con = connectionClass.CONN();
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select * from cart");

                flag=false;
                count=0;
                if(rs.first())
                    count=1;
                while(rs.next())
                    count++;

                ResultSet r;
                Statement st2 = con.createStatement();
                r=st2.executeQuery("select * from cart;");
                if(r.first())
                    getActivity().setTitle("FOODIE");

                //sc.setQueryHint("Search Here");
                itemPrice = new String[count];
                itemName = new String[count];
                int index=0;
                itemArrayList = new ArrayList<ListItem2>();
                itemArrayList.clear();
                if(rs.first())
                {
                    itemName[index] = rs.getString(1);
                    itemPrice[index] = rs.getString(2);
                    itemArrayList.add(new ListItem2(itemName[index],itemPrice[index], 0));

                }
                while(rs.next())
                {
                    index++;
                    itemName[index] = rs.getString(1);
                    itemPrice[index] = rs.getString(2);
                    itemArrayList.add(new ListItem2(itemName[index],itemPrice[index], 0));

                }

                /*sc.setIconified(false);
                sc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //customeAdapter.getFilter().filter(newText);
                        searchtext = newText.toString();
                        String sql = "select prod_id from product where prod_name LIKE '%"+searchtext+"%' and category_id = '"+MainActivity.cat_id+"'";
                        try {
                            Statement stmt = con.createStatement();
                            ResultSet r = stmt.executeQuery(sql);
                            flag=true;
                            int count=1;
                            if(r.first())
                                count=1;
                            while(r.next())
                                count++;
                            productidlist = new int[count+1];
                            Toast.makeText(getActivity(),"Length is "+count,Toast.LENGTH_SHORT).show();
                            count=0;
                            if(r.first())
                                productidlist[count++]=r.getInt(1);
                            while(r.next())
                                productidlist[count++]=r.getInt(1);
                            String loop = "List : ";
                            for (int i = 0 ; i < count; i++)
                            {
                                loop += Integer.toString(productidlist[i]);
                                loop += " ";
                            }
                            Toast.makeText(getActivity(),"List is "+loop,Toast.LENGTH_LONG).show();
                        }
                        catch (SQLException e)
                        {
                            Toast.makeText(getActivity(),"Exception "+e,Toast.LENGTH_LONG).show();
                        }
                        if (TextUtils.isEmpty(newText.toString())) {
                            listView2.clearTextFilter();
                        } else {
                            listView2.setFilterText(newText.toString());
                        }
                        return true;
                    }
                });*/

                /*listView2.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    Fragment fragment = null;
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if(flag)
                            MainActivity.prod_id = productidlist[position];
                        else
                            MainActivity.prod_id = (((MainActivity.cat_id-1)*10)+position+1);
                        MainActivity.position = position;
                        fragment = new DetailActivity();
                        if (fragment != null) {
                            int thisid = getId();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(thisid, fragment);
                            ft.commit();
                        } else {
                            Toast.makeText(getActivity(), "Null Fragment", Toast.LENGTH_SHORT).show();
                        }

                });*/
                /*customeAdapter = new BillActivity.CustomeAdapter2(getActivity().getApplicationContext(),itemArrayList);
                listView2.setAdapter(customeAdapter);

                listView2.setTextFilterEnabled(true);*/
                customeAdapter = new CustomeAdapter2(getActivity().getApplicationContext(),itemArrayList);
                listView2.setAdapter(customeAdapter);

                listView2.setTextFilterEnabled(true);

            } else {
                Toast.makeText(getActivity(), "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), "Exception : "+e, Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public class CustomeAdapter2 extends ArrayAdapter<String> {

        Context context;
        public ArrayList<ListItem2> itemArrayList;
        public ArrayList<ListItem2> orig;

        public CustomeAdapter2(Context context,ArrayList<ListItem2> itemArrayList) {
            super(context, R.layout.activity_bill);
            this.context = context;
            this.itemArrayList=itemArrayList;
        }

        @Override
        public int getCount() {
            return itemArrayList.size();
        }

        @Override
        public String getItem(int position) {
            return itemArrayList.get(position).toString();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            BillActivity.CustomeAdapter2.ListHolder holder;
            convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_bill, null);
            holder = new BillActivity.CustomeAdapter2.ListHolder();
            //ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_image);
            holder.name = (TextView) convertView.findViewById(R.id.desc);
            holder.price = (TextView) convertView.findViewById(R.id.price);

            //imageView.setImageResource(itemArrayList.get(position).getImage());
            /*holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int) view.getTag();
                    connectionClass = new ConnectionClass();
                    final Connection con = connectionClass.CONN();
                    Connection con1 = connectionClass.CONN();

                    Statement st1 ;
                    try {
                        st1 = con1.createStatement();
                        String sql2 = "insert into cart values('" + itemName[index] + "'," + itemPrice[index] + ");";
                        st1.execute(sql2);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });*/
            holder.name.setText(itemArrayList.get(position).getName());
            String pricetext = "";
            pricetext = "Rs. " + itemArrayList.get(position).getPrice();
            holder.price.setText(pricetext);

            return convertView;
        }

        public class ListHolder
        {
            TextView name;
            TextView price;
            int pos;
        }

        /*public Filter getFilter() {
            return new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    final FilterResults oReturn = new FilterResults();
                    final ArrayList<ListItem2> results = new ArrayList<ListItem2>();
                    if (orig == null)
                        orig = itemArrayList;
                    if (constraint != null) {
                        if (orig != null && orig.size() > 0) {
                            for (final ListItem2 g : orig) {
                                if (g.getName().toLowerCase()
                                        .contains(constraint.toString().toLowerCase()))
                                    re  sults.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                    return oReturn;
                }

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint,
                                              FilterResults results) {
                    itemArrayList = (ArrayList<ListItem2>) results.values;
                    notifyDataSetChanged();
                }
            };
        }*/
    }

    public class ListItem2 {

        private String name;
        private String price;

        public ListItem2(String name,String price,int image){
            this.name = name;
            this.price = price;
        }

        public String getName(){
            return name;
        }

        public String getPrice(){
            return price;
        }

    }

}
