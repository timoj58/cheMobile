package mobile.che.com.oddymobstar.chemobile.activity.handler;

import android.view.View;

import mobile.che.com.oddymobstar.chemobile.R;
import mobile.che.com.oddymobstar.chemobile.activity.ProjectCheActivity;
import mobile.che.com.oddymobstar.chemobile.activity.controller.ProjectCheController;
import mobile.che.com.oddymobstar.chemobile.fragment.ChatFragment;
import mobile.che.com.oddymobstar.chemobile.fragment.ConfigurationFragment;
import mobile.che.com.oddymobstar.chemobile.fragment.DeviceFragment;
import mobile.che.com.oddymobstar.chemobile.fragment.GridFragment;


/**
 * Created by timmytime on 06/12/15.
 */
public class FragmentHandler {

    private final ProjectCheActivity main;
    private final ProjectCheController controller;
    public ChatFragment chatFrag = new ChatFragment();
    public GridFragment gridFrag = new GridFragment();
    public DeviceFragment deviceFragment = new DeviceFragment();
    public ConfigurationFragment confFrag = new ConfigurationFragment();


    public FragmentHandler(ProjectCheActivity main, ProjectCheController controller) {
        this.main = main;
        this.controller = controller;
    }

    public void removeFragments(boolean backPressed) {

        android.support.v4.app.FragmentTransaction transaction = main.getSupportFragmentManager().beginTransaction();

        try {
            chatFrag.getHiddenChatPost().setVisibility(View.GONE);
        } catch (Exception e) {

        }


        try {
            transaction.remove(chatFrag);
        } catch (Exception e) {

        }


        try {
            gridFrag.getHiddenCreateView().setVisibility(View.GONE);
        } catch (Exception e) {

        }

        try {
            gridFrag.clearAdapter();
            transaction.remove(gridFrag);
        } catch (Exception e) {

        }


        try {
            transaction.remove(confFrag);
        } catch (Exception e) {

        }


        //what is this for?
        try {
            controller.materialsHandler.handleFABChange(-1, R.drawable.ic_search_white_24dp, View.INVISIBLE);
            controller.materialsHelper.navToolbar.setTitle(R.string.app_name);
            controller.materialsHelper.navToolbar.setBackgroundColor(main.getResources().getColor(android.R.color.holo_red_dark));
        } catch (Exception e) {

        }

        transaction.commit();

    }

}