package com.smyhvae.radiogrouptabdemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyhvae.radiogrouptabdemo.R;

import java.util.TimerTask;
/**
 * Created by smyh on 2015/4/28.
 */
public class FindFagment extends Fragment {
    private View view;
    TextView tv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find,null);
        return view;
    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }
    class  findbar extends Activity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            tv= (TextView) findViewById(R.id.toolbartext);
        }
    }
}
