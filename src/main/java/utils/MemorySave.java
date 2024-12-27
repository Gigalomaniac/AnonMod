package utils;

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class MemorySave {

    public ArrayList<String> relics;
    public ArrayList<Integer> relic_counters;
    public ArrayList<CardSave>  cards;
    public float  memoryX;

    public MemorySave(ArrayList<String> relics, ArrayList<Integer> relic_counters, ArrayList<CardSave>  cards,float memoryX) {
        this.relics = relics;
        this.relic_counters =relic_counters;
        this.cards =cards;
        this.memoryX =memoryX;
    }

}
