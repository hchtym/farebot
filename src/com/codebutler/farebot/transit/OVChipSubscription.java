/*
* OVCSubscription.java
*
* Copyright (C) 2012 Eric Butler
*
* Authors:
* Wilbert Duijvenvoorde <w.a.n.duijvenvoorde@gmail.com>
* Eric Butler <eric@codebutler.com>
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.codebutler.farebot.transit;

import android.os.Parcel;
import android.util.SparseArray;

import java.util.Date;

import com.codebutler.farebot.Utils;

public class OVChipSubscription extends Subscription {
    private final int mId;
    private final int mClasstype;
    private final long mValidFromDate;
    private final long mValidFromTime;
    private final long mValidToDate;
    private final long mValidToTime;
    private final int mTripFrom;
    private final int mTripTo;
    private final int mTripDate;
    private final int mTripTime;
    private final int mTripUnknown;
    private final int mTripCompany;
    private final int mUnknownDate;
    private final int mUnknownTime;
    private final int mUnknownCompany;
    private final int mUnknown1;
    private final int mAgency;
    private final int mMachineId;
    private final int mSubscription;
    private final int mSubscriptionAddress;
    private final int mType1;
    private final int mType2;
    private final int mUsed;
    private final int mRest;

    private static SparseArray<String> sSubscriptions = new SparseArray<String>() {
        /* It seems that all the IDs are unique, so why bother with the companies? */ {
            /* NS */
            put(0x0001, "NS-Jaarabonnement");
            put(0x0003, "NS-Jaarabonnement gezinslid");
            put(0x0005, "OV-jaarkaart");
            put(0x0007, "OV-Bijkaart");
            put(0x0009, "NS-Vervoer-legitimatiebewijs");
            put(0x000B, "NS-Vervoerbewijs");
            put(0x000E, "NS-Vervoerbewijs Ex.");
            put(0x0011, "NS Businesscard");
            put(0x0014, "Jaartrajectabonnement");
            put(0x0016, "Stad Streek-Jaarabonnement");
            put(0x0019, "Voordeelurenabonnement (twee jaar)");
            put(0x001A, "Voordeelurenabonnement");
            put(0x001B, "Voordeelurenabonnement 60+ doorlopend");
            put(0x001C, "Voordeelurenabonnement 60+");
            put(0x001D, "Voordeelurenabonnement");
            put(0x001E, "Kids Vrij");
            put(0x0028, "OV-chip Plus");
            put(0x0032, "Maandabon. Altijd Vrij");
            put(0x0033, "Maandabon. Altijd Voordeel");
            put(0x0034, "Maandabon. Altijd Voordeel");
            put(0x0054, "Railrunner");
            put(0x0055, "Dagkaart");
            put(0x0057, "VanAnaarBeter enkele reis");
            put(0x005A, "Keuzedag");
            put(0x005B, "Keuzedag VDU");
            put(0x005E, "Toeslag 2-1 Weekenddag");
            put(0x005F, "Toeslag 2-1 Dag");
            put(0x0060, "Toeslag 2-1 Daluren");
            put(0x0061, "Toeslag 2-1 Keuzedag");
            put(0x0078, "Dagkaart fiets");
            put(0x0079, "Dagkaart hond");
            put(0x007A, "ICE Toeslag enkele reis");
            put(0x00A2, "Stationsmedewerker pas lokaal");
            put(0x00A3, "Stationsmedewerker pas landelijk");
            put(0x00AF, "Studenten OV-chipkaart week (2009)");
            put(0x00B0, "Studenten OV-chipkaart weekend (2009)");
            put(0x00B1, "Studentenkaart korting week (2009)");
            put(0x00B2, "Studentenkaart korting weekend (2009)");
            put(0x00B4, "NS-Privilegepas/legitimatie");
            put(0x00B5, "NS-Privilegepas");
            put(0x00B6, "NS-Privilegepas Junior");
            put(0x00B7, "NS-Legitimatiebewijs");
            put(0x00B9, "NS-reisrecht RET personeel");
            put(0x00BA, "NS-reisrecht GVB personeel");
            put(0x00C9, "Reizen op saldo bij NS");
            put(0x00CA, "Reizen op saldo bij NS");
            put(0x00CD, "Voordeelurenabonnement reizen op saldo");
            put(0x00CE, "Voordeelurenabonnement reizen op saldo");
            put(0x00CF, "NS-Business Card Check in/out");
            put(0x00D0, "NS-Business Card Check in/out");
            put(0x00D1, "Dal Voordeel");
            put(0x00D2, "Dal Voordeel");
            put(0x00D3, "Weekend vrij");
            put(0x00D4, "Weekend vrij");
            put(0x00D5, "Dal Vrij");
            put(0x00D6, "Dal Vrij");
            put(0x00D7, "Altijd Voordeel");
            put(0x00D8, "Altijd Voordeel");
            put(0x00DB, "Altijd Vrij");
            put(0x00DC, "Altijd Vrij"); 
            put(0x00E5, "Reizen op saldo (tijdelijk eerste klas)");
            put(0x00E6, "Reizen op saldo (tijdelijk tweede klas)");
            put(0x00E7, "Reizen op saldo (tijdelijk eerste klas korting)");
            put(0x00E8, "Tijdelijke klassewissel korting");
            put(0x00E9, "BC vandaag 1e klasse");
            put(0x00EA, "BC vandaag 2e klasse");
            put(0x00F1, "rosmobility (Radiuz)");
            put(0x00F2, "rosmobility (Radiuz)");
            put(0x00F3, "rosmobility (Mobility Mixx)");
            put(0x00F4, "rosmobility (Mobility Mixx)");
            put(0x00F5, "Radiuz vandaag");
            put(0x00F7, "Mobility Mixx vandaag");
            put(0x0103, "Klassewissel");
            put(0x0104, "Klassewissel");
            put(0x0105, "Klassewissel");
            put(0x0106, "Klassewissel");
            put(0x0107, "Klassewissel");
            put(0x0108, "Klassewissel");
            put(0x010F, "Samenreiskorting");
            put(0x012B, "Activeren Kaartservicecode");
            put(0x012D, "Fyra toeslag enkele reis");
            put(0x012E, "Fyra Toeslag retour");
            put(0x012F, "Fyra Maandtoeslag");
            put(0x0130, "Fyra Altijd Toeslagvrij");
            put(0x0131, "Fyra Altijd Toeslagvrij");
            put(0x0137, "Fyra Toeslag enkele reis");
            /* Arriva */
            put(0x059A, "Dalkorting");
            /* Veolia */
            put(0x0626, "DALU Dalkorting");
            /* Connexxion */
            put(0x0692, "Daluren Oost-Nederland");
            put(0x069C, "Daluren Oost-Nederland");
            /* DUO */
            put(0x09C6, "Student weekend-vrij");
            put(0x09C7, "Student week-korting");
            put(0x09C9, "Student week-vrij");
            put(0x09CA, "Student weekend-korting");
            /* GVB */
            put(0x0BBD, "Fietssupplement");
        }
    };

    public static Creator<OVChipSubscription> CREATOR = new Creator<OVChipSubscription>() {
        public OVChipSubscription createFromParcel(Parcel parcel) {
            return new OVChipSubscription(parcel);
        }

        public OVChipSubscription[] newArray(int size) {
            return new OVChipSubscription[size];
        }
    };

    public OVChipSubscription(int subscriptionAddress, byte[] data, int type1, int type2, int used, int rest) {
        mSubscriptionAddress = subscriptionAddress;
        mType1 = type1;
        mType2 = type2;
        mUsed = used;
        mRest = rest;

        if (data == null) {
            data = new byte[48];
        }

        int id = 0;
        int company = 0;
        int subscription = 0;
        int classtype = 0;
        int validFromDate = 0;
        int validFromTime = 0;
        int validToDate = 0;
        int validToTime = 0;
        int tripFrom = 0;
        int tripTo = 0;
        int tripDate = 0;
        int tripTime = 0;
        int tripUnknown = 0;
        int tripCompany = 0;
        int unknownDate = 0;
        int unknownTime = 0;
        int unknownCompany = 0;
        int unknown1 = 0;
        int machineId = 0;

        int iBitOffset = 0;
        int fieldbits = Utils.getBitsFromBuffer(data, 0, 28);
        iBitOffset += 28;
        int subfieldbits = 0;
        int subfield2bits = 0;
        int subfield3bits = 0;

        if (fieldbits != 0x00) {
            if ((fieldbits & 0x0000200) != 0x00) {
                company = Utils.getBitsFromBuffer(data, iBitOffset, 8);
                iBitOffset += 8;
            }

            if ((fieldbits & 0x0000400) != 0x00) {
                subscription = Utils.getBitsFromBuffer(data, iBitOffset, 16);
                iBitOffset += 24;	/* skipping the first 8 bits, as they are not used OR don't belong to subscriptiontype at all */
            }

            if ((fieldbits & 0x0000800) != 0x00) {
                id = Utils.getBitsFromBuffer(data, iBitOffset, 24);
                iBitOffset += 24;
            }

            if ((fieldbits & 0x0002000) != 0x00) {
                classtype = Utils.getBitsFromBuffer(data, iBitOffset, 10);
                iBitOffset += 10;
            }

            if ((fieldbits & 0x0200000) != 0x00) {
                subfieldbits = Utils.getBitsFromBuffer(data, iBitOffset, 9);
                iBitOffset += 9;
            }

            if (subfieldbits != 0x00) {
                if ((subfieldbits & 0x0000001) != 0x00) {
                    validFromDate = Utils.getBitsFromBuffer(data, iBitOffset, 14);
                    iBitOffset += 14;
                }

                if ((subfieldbits & 0x0000002) != 0x00) {
                    validFromTime = Utils.getBitsFromBuffer(data, iBitOffset, 11);
                    iBitOffset += 11;
                }

                if ((subfieldbits & 0x0000004) != 0x00) {
                    validToDate = Utils.getBitsFromBuffer(data, iBitOffset, 14);
                    iBitOffset += 14;
                }

                if ((subfieldbits & 0x0000008) != 0x00) {
                    validToTime = Utils.getBitsFromBuffer(data, iBitOffset, 11);
                    iBitOffset += 11;
                }

                if ((subfieldbits & 0x0000010) != 0x00) {
                    if ((fieldbits & 0x0400000) != 0x00) {
                        subfield2bits = Utils.getBitsFromBuffer(data, iBitOffset, 16);
                        iBitOffset += 16;

                        if ((subfield2bits & 0x0000003) != 0x00) { // Don't know what every single bit represents at the moment, so just get everything for now
                            tripFrom = Utils.getBitsFromBuffer(data, iBitOffset, 16);
                            iBitOffset += 16;

                            tripTo = Utils.getBitsFromBuffer(data, iBitOffset, 16);
                            iBitOffset += 16;

                            subfield3bits = Utils.getBitsFromBuffer(data, iBitOffset, 4);
                            iBitOffset += 4;

                            tripDate = Utils.getBitsFromBuffer(data, iBitOffset, 14);
                            iBitOffset += 14;

                            tripTime = Utils.getBitsFromBuffer(data, iBitOffset, 11);
                            iBitOffset += 11;

                            tripUnknown = Utils.getBitsFromBuffer(data, iBitOffset, 8);
                            iBitOffset += 8;

                            // Company could be less bits and/thus the unknown one could be more
                            tripCompany = Utils.getBitsFromBuffer(data, iBitOffset, 8);
                            iBitOffset += 8;
                        }
                    } else {
                        // For now, let's just skip the bits if we don't know what they are
                        iBitOffset += 93;
                    }
                } else {
                    subfield2bits = Utils.getBitsFromBuffer(data, iBitOffset, 12);
                    iBitOffset += 12;

                    unknownDate = Utils.getBitsFromBuffer(data, iBitOffset, 14);
                    iBitOffset += 14;

                    unknownTime = Utils.getBitsFromBuffer(data, iBitOffset, 11);
                    iBitOffset += 11;

                    if ((subfield2bits & 0x0000010) != 0x00) {
                        iBitOffset += 16; // Unknown at this time, so just skipping for now
                    } else if ((subfield2bits & 0x0000100) != 0x00) {
                        iBitOffset += 44; // Unknown at this time, so just skipping for now
                    } else {
                        unknown1 = Utils.getBitsFromBuffer(data, iBitOffset, 8);
                        iBitOffset += 8;

                        // Company could be less bits and/thus the unknown one could be more
                        unknownCompany = Utils.getBitsFromBuffer(data, iBitOffset, 8);
                        iBitOffset += 8;
                    }
                }
            }

            if ((fieldbits & 0x0800000) != 0x00) {
                machineId = Utils.getBitsFromBuffer(data, iBitOffset, 24);
                iBitOffset += 24;
            }
        } else {
            throw new IllegalArgumentException("Not valid");
        }

        mId             = id;
        mAgency         = company;
        mSubscription   = subscription;
        mClasstype      = classtype;
        mValidFromDate  = validFromDate;
        mValidFromTime  = validFromTime;
        mValidToDate    = validToDate;
        mValidToTime    = validToTime;
        mTripFrom       = tripFrom;
        mTripTo         = tripTo;
        mTripDate       = tripDate;
        mTripTime       = tripTime;
        mTripUnknown    = tripUnknown;
        mTripCompany    = tripCompany;
        mUnknownDate    = unknownDate;
        mUnknownTime    = unknownTime;
        mUnknownCompany = unknownCompany;
        mUnknown1       = unknown1;
        mMachineId      = machineId;
    }

    public OVChipSubscription(Parcel parcel) {
        mId             = parcel.readInt();
        mClasstype      = parcel.readInt();
        mValidFromDate  = parcel.readLong();
        mValidFromTime  = parcel.readLong();
        mValidToDate    = parcel.readLong();
        mValidToTime    = parcel.readLong();
        mTripFrom       = parcel.readInt();
        mTripTo         = parcel.readInt();
        mTripDate       = parcel.readInt();
        mTripTime       = parcel.readInt();
        mTripUnknown    = parcel.readInt();
        mTripCompany    = parcel.readInt();
        mUnknownDate    = parcel.readInt();
        mUnknownTime    = parcel.readInt();
        mUnknownCompany = parcel.readInt();
        mUnknown1       = parcel.readInt();
        mAgency         = parcel.readInt();
        mMachineId      = parcel.readInt();
        mSubscription   = parcel.readInt();

        mSubscriptionAddress = parcel.readInt();
        mType1               = parcel.readInt();
        mType2               = parcel.readInt();
        mUsed                = parcel.readInt();
        mRest                = parcel.readInt();
    }

    public static String getSubscriptionName(int subscription) {
        if (sSubscriptions.get(subscription) != null) {
            return sSubscriptions.get(subscription);
        }
        return "Unknown Subscription (0x" + Long.toString(subscription, 16) + ")";
    }

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public Date getValidFrom() {
        if (mValidFromTime != 0)
            return OVChipTransitData.convertDate((int) mValidFromDate, (int) mValidFromTime);
        else
        	return OVChipTransitData.convertDate((int) mValidFromDate);
    }

    @Override
    public Date getValidTo() {
    	if (mValidToTime != 0)
            return OVChipTransitData.convertDate((int) mValidToDate, (int) mValidToTime);
        else
        	return OVChipTransitData.convertDate((int) mValidToDate);
    }

    @Override
    public String getSubscriptionName() {
        return getSubscriptionName(mSubscription);
    }

    @Override
    public String getActivation() {
        if (mType1 != 0) {
            return mUsed != 0 ? "Activated and used" : "Activated but not used";
        }
        return "Deactivated";
    }

    @Override
    public String getClasstype() {
        if (mClasstype == 0x140) {
            return "1e klas";
        } else if (mClasstype == 0x180) {
            return "2e klas";
        } else {
            return null;
        }
    }

    @Override
    public int getTripCompany() {
        return mTripCompany;
    }

    @Override
    public int getTripFrom () {
        return mTripFrom;
    }

    @Override
    public int getTripTo () {
        return mTripTo;
    }

    @Override
    public Date getTripDate () {
        return OVChipTransitData.convertDate((int) mTripDate);
    }

    @Override
    public Date getTripTime () {
        return OVChipTransitData.convertDate((int) mTripTime);
    }

    public String getTripCompanyName () {
        return OVChipTransitData.getShortAgencyName((int)mUnknownCompany);    // Nobody uses most of the long names
    }

    public Date getUnknownDate () {
        return OVChipTransitData.convertDate((int) mUnknownDate);
    }

    public Date getUnknownTime () {
        return OVChipTransitData.convertDate((int) mUnknownTime);
    }

    public String getUnknownCompany () {
        return OVChipTransitData.getShortAgencyName((int)mUnknownCompany);    // Nobody uses most of the long names
    }

    @Override
    public int getMachineId () {
        return mMachineId;
    }

    @Override
    public String getAgencyName () {
        return OVChipTransitData.getShortAgencyName((int)mAgency);    // Nobody uses most of the long names
    }

    @Override
    public String getShortAgencyName () {
        return OVChipTransitData.getShortAgencyName((int)mAgency);
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mId);
        parcel.writeInt(mClasstype);
        parcel.writeLong(mValidFromDate);
        parcel.writeLong(mValidFromTime);
        parcel.writeLong(mValidToDate);
        parcel.writeLong(mValidToTime);
        parcel.writeInt(mTripFrom);
        parcel.writeInt(mTripTo);
        parcel.writeInt(mTripDate);
        parcel.writeInt(mTripTime);
        parcel.writeInt(mTripUnknown);
        parcel.writeInt(mTripCompany);
        parcel.writeInt(mUnknownDate);
        parcel.writeInt(mUnknownTime);
        parcel.writeInt(mUnknownCompany);
        parcel.writeInt(mUnknown1);
        parcel.writeInt(mAgency);
        parcel.writeInt(mMachineId);
        parcel.writeInt(mSubscription);
        parcel.writeInt(mSubscriptionAddress);
        parcel.writeInt(mType1);
        parcel.writeInt(mType2);
        parcel.writeInt(mUsed);
        parcel.writeInt(mRest);
    }
}