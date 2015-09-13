package com.jude.fishing.module.place;

import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.fishing.R;
import com.jude.fishing.model.bean.PlaceBrief;

/**
 * Created by Mr.Jude on 2015/9/11.
 */
@RequiresPresenter(PlacePresenter.class)
public class PlaceFragment extends BeamListFragment<PlacePresenter,PlaceBrief> {

    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setLoadmoreAble(true);
    }

    @Override
    public int getLayout() {
        return R.layout.include_recyclerview;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new PlaceViewHolder(viewGroup);
    }
}
