package com.nalan.discoveryspace.data;

import androidx.fragment.app.FragmentManager;
import com.nalan.discoveryspace.R;
import com.nalan.discoveryspace.data.constant.AppConstants;
import com.nalan.discoveryspace.data.view.MainActivity;
import com.nalan.discoveryspace.data.view.MarsPhotosFragment;

import javax.inject.Inject;

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        containerId = R.id.contentFrame;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }
    public void navigateToHomeFragment() {
        MarsPhotosFragment fragment = new MarsPhotosFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, AppConstants.FRAGMENT_TAG.MARS_PHOTOS)
                .commitAllowingStateLoss();
    }
}
