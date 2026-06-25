import java.util.Optional;

public class TennisGame2 implements TennisGame {
    public int P1point = 0;
    public int P2point = 0;

    public String P1res = "";
    public String P2res = "";
    private String player1Name;
    private String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        var player1PointName = getPointName(P1point);
        var player2PointName = getPointName(P2point);
        var pointDifference = Math.abs(P1point - P2point);

        if (pointDifference == 0) {
            return getDrawScore();
        }

        if (pointDifference >= 2 && Math.max(P1point, P2point) >= 4) {
            return "Win for " + getLeadingPlayer();
        }

        if (player1PointName.isEmpty() || player2PointName.isEmpty()) {
            return "Advantage " + getLeadingPlayer();
        }

        return player1PointName.get() + "-" + player2PointName.get();
    }

    public void SetP1Score(int number) {
        for (int i = 0; i < number; i++) {
            P1Score();
        }
    }

    public void SetP2Score(int number) {
        for (int i = 0; i < number; i++) {
            P2Score();
        }
    }

    public void P1Score() {
        P1point++;
    }

    public void P2Score() {
        P2point++;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            P1Score();
        else
            P2Score();
    }

    private String getDrawScore() {
        assert P1point == P2point;
        return switch (P1point) {
            case 0:
                yield "Love-All";
            case 1:
                yield "Fifteen-All";
            case 2:
                yield "Thirty-All";
            case 3, 4:
                yield "Deuce";
            default:
                throw new IllegalStateException();
        };
    }

    private Optional<String> getPointName(int point) {
        return Optional.ofNullable(switch (point) {
            case 0:
                yield "Love";
            case 1:
                yield "Fifteen";
            case 2:
                yield "Thirty";
            case 3:
                yield "Forty";
            default:
                yield null;
        });
    }

    private String getLeadingPlayer() {
        if (P1point > P2point) {
            return player1Name;
        } else {
            return player2Name;
        }
    }
}