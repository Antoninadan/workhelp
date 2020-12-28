package ua.i.mail100.accountstatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// CME-22808
public class BitApp {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 1, 3, 4, 8, 11, 12, 16, 19, 24, 27, 32,  33, 34, 35, 39,
                43, 47, 49, 50, 51, 55, 59, 63, 65568, 65569, 131072, 131088);

        List<Integer> ACTIVE = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 47) == 35)
            ) ACTIVE.add(each);
        }
        System.out.println("ACTIVE = " + ACTIVE);

        List<Integer> ARREST = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 35) != 0)
                            && ((each & 2) == 0)
                            && ((each & 0x10000) == 0)
            ) ARREST.add(each);
        }
        System.out.println("ARREST = " + ARREST);

        List<Integer> BANK_ARREST = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 35) != 0)
                            && ((each & 2) == 0)
                            && ((each & 0x10000) != 0)
            ) BANK_ARREST.add(each);
        }
        System.out.println("BANK_ARREST = " + BANK_ARREST);

        //
        List<Integer> PRECLOSING = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 35) != 0)
                            && ((each & 1) == 0)
            ) PRECLOSING.add(each);
        }
        System.out.println("PRECLOSING = " + PRECLOSING);

        //
        List<Integer> BLOCKED = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 35) != 0)
                            && ((each & 32) == 0)
            ) BLOCKED.add(each);
        }
        System.out.println("BLOCKED = " + BLOCKED);

//
        List<Integer> TEMPORARY = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 39) == 39)
            ) TEMPORARY.add(each);
        }
        System.out.println("TEMPORARY = " + TEMPORARY);

//
        List<Integer> WAITING_NOTIFICATION = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 47) == 43)
            ) WAITING_NOTIFICATION.add(each);
        }
        System.out.println("WAITING_NOTIFICATION = " + WAITING_NOTIFICATION);

//
        List<Integer> UNDISCOVERED = new ArrayList<>();
        for (Integer each : list) {
            if (
                    ((each & 35) == 35)
                            && ((each & 12) != 0)
            ) UNDISCOVERED.add(each);
        }
        System.out.println("UNDISCOVERED = " + UNDISCOVERED);

        List<Integer> LOADING_ACCOUNT = new ArrayList<>();
        for (Integer each : list) {
            if ((each & 0x40) != 0)
                LOADING_ACCOUNT.add(each);
        }
        System.out.println("LOADING_ACCOUNT = " + LOADING_ACCOUNT);


// attention!!!!!!!!!
        List<Integer> CLOSED = new ArrayList<>();
        for (Integer each:list) {
            if(
                    ((each & 35) ==0)
            ) CLOSED.add(each);
        }
        System.out.println("CLOSED !!!!!!!! = " + CLOSED);


        List<Integer> aggregation = new ArrayList<>();
        aggregation.addAll(ACTIVE);
        aggregation.addAll(ARREST);
        aggregation.addAll(BANK_ARREST);
        aggregation.addAll(PRECLOSING);
        aggregation.addAll(BLOCKED);
        aggregation.addAll(TEMPORARY);
        aggregation.addAll(WAITING_NOTIFICATION);
        aggregation.addAll(UNDISCOVERED);
        aggregation.addAll(LOADING_ACCOUNT);
        aggregation.addAll(CLOSED);



        System.out.println("if each position is used in all status: " + aggregation.containsAll(list));
        List<Integer> difference = new ArrayList(list);
        difference.removeAll(aggregation);
        System.out.println("difference in positions = " + difference);


        ///
        List<Integer> temp = Arrays.asList(21233101,120297499,120297620,133995385,138537854,138809949,139086372,139102430,139214862,139215266,3253845,10024152,8875401,4976199,8926978,11742728,4231029,10697148,7088179,5439732,110168,5019126,11789648,14018378,14015546,14031293,13979226,14015531);

        List<Integer> undiscov = Arrays.asList(139409459,21233101,64206288,64206113,64206338,93337491,93337420,93337507,102707464,102530822,102531258,102709646,102532941,102533031,102712362,102548395,102550200,102550829,102714169,102552158,102552929,102552980,102553634,102728907,102729300,102556496,102556593,102559986,102560006,102560109,102734471,102563795,102563923,102564307,102566716,102737672,102738003,102567851,102567882,102568683,102709137,102709626,102572353,102573579,102574484,102574599,102574800,102713808,102713921,102726027,102726394,102584906,139337458,102731992,102613568,102733948,102734839,102623330,102735749,102637527,102737733,102638724,102639299,102650663,102650669,102650740,102740892,102651149,102651209,102651475,102741447,102741665,102655965,102743294,102743462,102659960,102660103,102743742,102743787,102703726,102746030,102704911,102706633,120297499,120297620,133995385,137621983,138537854,138809949,139086372,139102430,139214862,139215266,139333041,139333391,139333558,139333534,139333548,139333542,139337467,139337505,139337522,139403638,139404819,139404882,139404946,139405032,139405007,139405015,139405026,139405063,139405046,139405038,139405057,139405351,139405433,139405641,139405646,139405772,139405777,139405832,139405839,139405844,139406586,139407012,139407024,139407224,139408316,139409302,139409345,139409427,139409433,139409473,139409488,3253845,29206783,29206777,29206789,139384987,139409387,139409502,139333608,139333613,139335654,139336883,139337292,139333346,139333350,139333418,139333452,139333491,139333521,139333527,139385054,139353796,139385027,139405594,139405598,139409049,139409053,139409152,139409506,136872228,139331925,139331929,139405075,139405069,139405087,139405081,139405254,139407032,139407143,139407297,139333282,139331995,139406136,139405116,139405099,139405091,139405110,10024152,8875401,4976199,8926978,11742728,4231029,10697148,7088179,5439732,110168,5019126,11789648,19118209,59519174,127449672,14018378,14015546,14031293,13979226,14015531,29206664,29206779,29206785,29206791,26966322,30872768,24537279);

        List<Integer> differenceTempUndiscov = new ArrayList(temp);
        differenceTempUndiscov.removeAll(undiscov);

        List<Integer> differenceTempUndiscov2 = new ArrayList(temp);
        differenceTempUndiscov2.removeAll(differenceTempUndiscov);

        System.out.println(differenceTempUndiscov2);

    }
}

