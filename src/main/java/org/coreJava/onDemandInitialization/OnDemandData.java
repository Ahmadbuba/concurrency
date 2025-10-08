package org.coreJava.onDemandInitialization;


/**
 * Author: Ahmad Buba
 * Date: 10/7/25
 */

public class OnDemandData {
    /* private constructor to ensure only one object is constructed */
    private OnDemandData() {

    }

    public static OnDemandData getInstance() {
        return Holder.INSTANCE;
    }

    /* only initiated on first use, i.e. in the first call to getInstance */
    private static class Holder {
        /* VM guarantees that this happens at most once */
        static final OnDemandData INSTANCE = new OnDemandData();
    }
}
