package pathes;

public enum Move {
    AWAKE(0),
    DEBUFF(1),
    ATTACK(2),
    SLEEP(3),
    ABORT(4),
    MULATTACK(5),
    BUFF(6),
    UNKNOWN(7),
    DEFAULT(-1);

    public int id;

    Move(int id) {
        this.id = id;
    }

    public static Move getMove(byte id) {
        switch (id) {
            case 0:
                return AWAKE;
            case 1:
                return DEBUFF;
            case 2:
                return ATTACK;
            case 3:
                return SLEEP;
            case 4:
                return ABORT;
            case 5:
                return MULATTACK;
            case 6:
                return BUFF;
            case 7:
                return UNKNOWN;
        }
        return DEFAULT;
    }
}
