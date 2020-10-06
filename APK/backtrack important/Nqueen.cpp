#include <bits/stdc++.h>
using namespace std;
#define n 4
//isSafe Fn , we have board matrix and x,y cords where we want to place queen
bool isSafe(int board[n][n], int x, int y)
{
    // check if a queen is present in same column , above
    for (int i = 0; i < x; i++)
    {
        if (board[i][y])
        {
            return false;
        }
    }
    // check if a queen is present in same row, left
    for (int i = 0; i < y; i++)
    {
        if (board[x][i])
        {
            return false;
        }
    }
    // check if a queen is present at diagonal
    /* Check upper diagonal on left side */
    for (int i = x, j = y; i >= 0 && j >= 0; i--, j--)
        if (board[i][j])
            return false;

    /* Check lower diagonal on left side */
    for (int i = x, j = y; j >= 0 && i < n; i++, j--)
        if (board[i][j])
            return false;
    return true;
}
void printSolution(int board[n][n])
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
}
bool solveNQutil(int board[n][n], int col)
{
    if (col == n)
    {
         printSolution(board);
         cout<<endl;
        return true;
    }
    bool result=false;
    for (int i = 0; i < n; i++)
    {
        if (isSafe(board, i, col))
        {
            board[i][col] = 1;
            result = solveNQutil(board, col + 1)||result;
            // if (solveNQutil(board, col + 1))
            // {
            //     return true;
            // }
            board[i][col] = 0;
        }
    }
    return result;
}

void solveNQueen()
{
    int board[n][n] = {};
    if (solveNQutil(board, 0))
    {
        cout<<"";
    }
    else
    {
        cout << "solution doesn't exist" << endl;
    }
}
int main()
{
    solveNQueen();
    return 0;
}