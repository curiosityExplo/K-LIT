package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Speakers extends Fragment {
    private View view;
    private ArrayList<SpeakersBio> speakersBioArrayList = new ArrayList<SpeakersBio>();
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_speakers, container, false);
        listView = (ListView) view.findViewById(R.id.speakers_listview);
        SpeakersBio speakersBio = new SpeakersBio();

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker1_abha_iyengar);
        speakersBio.setSpeakerName("Abha Iyengar");
        speakersBio.setSpeakerBio("An internationally published author, poet and British Council certified creative writing facilitator.Her poems have appeared in the Anthology of Contemporary Indian Poets and her fiction in The Indo-Australian Anthology of Short Fiction.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker2_aditya_bal);
        speakersBio.setSpeakerName("Aditya Bal");
        speakersBio.setSpeakerBio("A model/actor turned Chef, Aditya is one of the most recognized faces and has hosted slew ofsuccessful shows for the NDTV Goodtimes. He is finishing his first book on food and working on new show for the Epic Channel.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker3_advaita_kala);
        speakersBio.setSpeakerName("Advaita Kala");
        speakersBio.setSpeakerBio("A critical and commercial success, Advaita shot to fame with her witty classic, Almost Single.A hotelier by profession she is also the literary brain behind Sujoy Gosh’s much acclaimed Kahani.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker4_ajay_jain);
        speakersBio.setSpeakerName("Ajay Jain");
        speakersBio.setSpeakerBio("An avid traveller, writer and owner of the Kuzum Café, in Hauz Khas Village,a travellars’s adda to share and converse travel tales over complimentary coffee and cookies.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker5_akshay_manwani);
        speakersBio.setSpeakerName("Akshay Manwani");
        speakersBio.setSpeakerBio("Having chucked away the corporate life, Akshay found his calling in literature.His first book Sahir Ludhianivi: A People’s Poet, was published by Harper Collins in 2013,and he is currently working on his third book on Nasir Hussain.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker6_alka_pande);
        speakersBio.setSpeakerName("Alka Pande");
        speakersBio.setSpeakerBio("Dr Pande is currently a Consultant Arts Advisor and Curator of the Visual Arts Gallery,India Habitat Centre. She has authored several well-acclaimed books, with a strong focus on Indian arts and ancient Indian erotic literature and aesthetics.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker7_ameena_saiyid);
        speakersBio.setSpeakerName("Ameena Saiyid");
        speakersBio.setSpeakerBio("She is the managing director of Oxford University Press in Pakistan and the founder of Karachi Literature Festival and Islamabad Literature Festival. In 2010, she became the first woman to be elected president of the 150-year-old Overseas Investors Chamber of Commerce and Industry (OICCI).");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker8_amit_shankar);
        speakersBio.setSpeakerName("Amit Shankar");
        speakersBio.setSpeakerBio("He has authored 4 national best sellers; Flight of the Hilsa, Chapter 11, Love is Vodka-A Shot Ain’t Enough and Café Latte. A coffee addict, an avid music buff and a great exponent of guitar, his genre includes rock, blues and jazz.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker9_aanchal_malhotra);
        speakersBio.setSpeakerName("Aanchal Malhotra");
        speakersBio.setSpeakerBio("She is a multimedia artist whose works explore the written word, photographic imagery and traditional printmaking. She is currently working on a project entitled, 'Remnants of a Separation' - a study of the material remains of the Partition of the Indian Subcontinent in 1947.");
        speakersBioArrayList.add(speakersBio);

        speakersBio = new SpeakersBio();
        speakersBio.setSpeakerPhoto(R.mipmap.speaker10_anuja_chauhan);
        speakersBio.setSpeakerName("Anuja Chauhan");
        speakersBio.setSpeakerBio("She is often described as 'the best writer of the Indian commercial fiction genre\".As a writer, she is best known for her best-selling contemporary 'rom-com' novels, The Zoya Factor, Battle For Bittora, Those Pricey Thakur Girls and The House That BJ Built.");
        speakersBioArrayList.add(speakersBio);

        listView.setAdapter(new SpeakersAdapter(getActivity().getBaseContext(), speakersBioArrayList));
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Speakers");
    }
}
