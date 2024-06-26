package level02.N159993;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다. 
 * 각 칸은 통로 또는 벽으로 구성되어 있으며, 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다. 
 * 통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데, 이 문은 레버를 당겨서만 열 수 있습니다. 레버 또한 통로들 중 한 칸에 있습니다. 
 * 따라서, 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다. 
 * 이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다. 
 * 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때, 최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.
미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요. 만약, 탈출할 수 없다면 -1을 return 해주세요.
 */
public class Main {
    
    public static int[] row = new int[]{0, 1, 0, -1};
    public static int[] col = new int[]{1, 0, -1, 0};

    public static int rowSize;
    public static int colSize;
    public static char[][] miro;

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        Main m = new Main();
        
        String[] case01 = new String[]{"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(m.solution(case01));

        String[] case02 = new String[]{"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
        System.out.println(m.solution(case02));

    }

    public int solution(String[] maps) {
        rowSize = maps.length;
        colSize = maps[0].length();

        int startX = 0;
        int startY = 0;
        int buttonX = 0;
        int buttonY = 0;
        int exitX = 0;
        int exitY = 0;

        miro = new char[rowSize][colSize];

        // Array setting
        for (int i = 0; i < rowSize; i++) {
            for (int k = 0; k < colSize; k++) {
                char current = maps[i].charAt(k);
                if (current == 'S') {
                    startX = i;
                    startY = k;
                } else if (current == 'L') {
                    buttonX = i;
                    buttonY = k;
                } else if (current == 'E') {
                    exitX = i;
                    exitY = k;
                }
                miro[i][k] = current;
            }
        }

        // start to button
        int toButton = findGoal(startX, startY, buttonX, buttonY);
        // button to exit
        int toExit = findGoal(buttonX, buttonY, exitX, exitY);
        
        if (toButton == -1 || toExit == -1) {
            return -1;
        }

        return toButton + toExit; 
    }

    public static int findGoal(int startX, int startY, int goalX, int goalY) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;
        int fastWay = 0;

        while (!queue.isEmpty()) {
            int currentQueueSize = queue.size();

            for (int i = 0; i < currentQueueSize; i++) {
                Point p = queue.poll();
                int x = p.x;
                int y = p.y;

                if (x == goalX && y == goalY) {
                    return fastWay;
                }

                for (int k = 0; k < 4; k++) {
                    int currentX = x + row[k];
                    int currentY = y + col[k];

                    if (currentX >= rowSize || currentX < 0 || currentY >= colSize || currentY < 0 || miro[currentX][currentY] == 'X' || visited[currentX][currentY]) {
                        continue;
                    } 

                    queue.offer(new Point(currentX, currentY));
                    visited[currentX][currentY] = true;
                }
            }
            fastWay++;
        }

        return -1;
    }
}