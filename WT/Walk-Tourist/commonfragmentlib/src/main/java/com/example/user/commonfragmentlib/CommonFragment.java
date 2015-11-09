package com.example.user.commonfragmentlib;

/*
    WT_FragmentManager
    ├ WT_MainDisplayFragment
    │ ├ MDF_Game_Contents
    │ ├ MDF_PointManagement
    │ ├ MDF_TouristSpot
    │ │ ├ MDF_TouristSpot_ForBor
    │ │ ├ MDF_TouristSpot_ForPref
    │ │ └ MDF_TouristSpot_ForSpot
    │ ├ MDF_Help
    │ └ MDF_Map
    ├ WT_PartsDisplayFragment
    │ ├ WT_PDF_Menu
    │ ├ WT_PDF_XXX
    │ ├ WT_PDF_XXX
    │ ├ WT_PDF_XXX
    │ └ WT_PDF_XXX
    └ WT_DialogDisplayFragment
       ├ WT_DDF_Connected
       └ WT_DDF_Error
 */

import android.app.Fragment;

/**
 * Created by Akira on 2015/05/24.
 * アプリ内で使用するFragmentを管理するクラス
 */
public abstract class CommonFragment extends Fragment {

    protected int m_LayoutWidth;
    protected int m_LayoutHeight;

    public CommonFragment() {}

    public int getLayoutWidth()
    {
        return m_LayoutWidth;
    }

    public int getLayoutHeight()
    {
        return m_LayoutHeight;
    }

}