#include <bits/stdc++.h>
using namespace std;
#define n 4
int sol[n][n] = {};
bool isSafe(int maze[n][n], int x, int y)
{
    if (x < n && y < n && maze[x][y] != 0)
    {
        return true;
    }
    return false;
}
bool solveMazeUtil(int maze[n][n], int rat_x, int rat_y, int dest_x, int dest_y)
{
    if (rat_x == dest_x && rat_y == dest_y)
    {
        sol[rat_x][rat_y] = 1;
        return true;
    }
    if (isSafe(maze, rat_x, rat_y))
    {
        sol[rat_x][rat_y] = 1;
        // possible moves? +1 on x or +1 on y
        // check if its possible to move on x-axis
        //else if check if possible to move on y-axis
        // else we backtrack
        if (solveMazeUtil(maze, rat_x + 1, rat_y, dest_x, dest_y))
        {
            return true;
        }
        if (solveMazeUtil(maze, rat_x, rat_y + 1, dest_x, dest_y))
        {
            return true;
        }

        sol[rat_x][rat_y] = 0;
    }
}
void RatInMaze(int maze[n][n], int rat_x, int rat_y, int dest_x, int dest_y)
{
    if (solveMazeUtil(maze, 0, 0, n - 1, n - 1))
    {
        //print solution
        // we keep 1 where rat travels
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                cout << sol[i][j] << " ";
            }
            cout << endl;
        }
    }
}
int main()
{
    int maze[n][n];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> maze[i][j];
        }
    }
    RatInMaze(maze, 0, 0, n - 1, n - 1);

    return 0;
}