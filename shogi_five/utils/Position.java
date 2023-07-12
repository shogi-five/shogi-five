
package shogi_five.utils;

import java.util.Optional;

public enum Position {
    A1, A2, A3, A4, A5,
    B1, B2, B3, B4, B5,
    C1, C2, C3, C4, C5,
    D1, D2, D3, D4, D5,
    E1, E2, E3, E4, E5;

    public Position inverse() {
        return Position.values()[24 - this.ordinal()];
    }

    public Optional<Position> up() {
        int up = this.ordinal() - 5;
        if (up < 0) {
            return Optional.empty();
        }

        return Optional.of(Position.values()[up]);
    }
    public Optional<Position> up(boolean inverse) {
        if (inverse) {
            return this.down();
        }
        return this.up();
    }




    public Optional<Position> down() {
        int down = this.ordinal() + 5;
        if (down> 25) {
            return Optional.empty();
        }

        return Optional.of(Position.values()[down]);
    }
    public Optional<Position> down(boolean inverse) {
        if (inverse) {
            return this.up();
        }
        return this.down();
    }

    public Optional<Position> left() {
        int left = this.ordinal()-1;
        
        if (left%5 == 4) {
            return Optional.empty();
        }

        return Optional.of(Position.values()[left]);
    }
    public Optional<Position> left(boolean inverse) {
        if (inverse) {
            return this.right();
        }
        return this.left();
    }

    public Optional<Position> right() {
        int right = this.ordinal()+1;
        
        if (right%5 == 0) {
            return Optional.empty();
        }

        return Optional.of(Position.values()[right]);
    }
    public Optional<Position> right(boolean inverse) {
        if (inverse) {
            return this.left();
        }
        return this.right();
    }


}   
