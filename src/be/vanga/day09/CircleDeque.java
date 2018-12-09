package be.vanga.day09;

import java.util.ArrayDeque;

// https://www.reddit.com/r/adventofcode/comments/a4i97s/2018_day_9_solutions/ebetdc5
public class CircleDeque<T> extends ArrayDeque<T> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 360702548993176841L;

	public void rotate(int num) {
        if (num == 0) return;
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                T t = this.removeLast();
                this.addFirst(t);
            }
        } else {
            for (int i = 0; i < Math.abs(num) - 1; i++) {
                T t = this.remove();
                this.addLast(t);
            }
        }
    }
}
