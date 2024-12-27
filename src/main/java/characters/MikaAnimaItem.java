package characters;


public enum MikaAnimaItem implements AnimaItem {
//    CAFE_REACTION(0),
//    Formation_Idle(1),
//    Formation_Pickup(2),
//    Callsign(3),
//    MOVE_END(4),
//    MOVING(5),
//    ATTACK_IDLE(6),
//    ATTACK_END(7),
//    ATTACKING(8),
//    ATTACK_START(9),
//    RELOAD(11),
//    Victory_End(12),
//    Victory_Start(13),
//    DYING(14),
//    PANIC(15);
    Cafe_Idle(0),
    Cafe_Reaction(1),
    Cafe_Walk(2),
    Formation_Idle(3),
    Formation_Pickup(4),
    Kneel_Attack_Delay(5),
//    Kneel_Attack_End(6),
    Kneel_Attack_Ing(6),
    Kneel_Attack_Start(7),
    Kneel_Idle(8),
    Kneel_Reload(9),
    Move_End_Kneel(10),
    Move_End_Normal(11),
    Move_Ing(12),
    Move_Jump(13),
    Normal_Attack_Delay(14),
    Normal_Attack_End(15),
    Normal_Attack_Ing(16),
    Normal_Attack_Start(17),
    Normal_Callsign(18),
    Normal_Idle(19),
    Normal_Reload(20),
    Normal_Reload_Random(21),
    Victory_End(22),
    Victory_End_Random(23),
    Victory_Start(24),
    Victory_Start_Random(25),
    Vital_Death(26),
    Vital_Dying_Ing(27),
    Vital_Panic(28),;
    private int i;

    public int get() {
        return this.i;
    }

    private MikaAnimaItem(int i) {
        this.i = i;
    }
}
