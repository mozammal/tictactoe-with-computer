import React from 'react';
import ReactDOM from 'react-dom';
import SockJsClient from 'react-stomp';
import './index.css';
import App from './App';


function Square(props) {
    return (
        <button className="square" onClick={props.onClick}>
            {props.value}
        </button>
    );
}

class Board extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            board: Array(9).fill(null),
            xIsNext: true,
        };
    }

    sendMessage = (msg) => {
        this.clientRef.sendMessage('/app/emit.game.event',
            JSON.stringify({"board": msg}));
    }


    updateState(board) {
        /* if (calculateWinner(board)) {
             return;
         }*/
        this.setState({
            board: board,
            xIsNext: !this.state.xIsNext,
        });
    }

    handleClick(i) {
        const board = this.state.board.slice();
        if (calculateWinner(board) || board[i]) {
            return;
        }
        if (isGameEnd(board))
            return;
        board[i] = this.state.xIsNext ? 'X' : 'O';
        this.setState({
            board: board,
            xIsNext: !this.state.xIsNext,
        });
        if (calculateWinner(board) === null) {
            this.sendMessage(board);
        }
    }

    renderSquare(i) {
        return (
            <Square
                value={this.state.board[i]}
                onClick={() => this.handleClick(i)}
            />
        );
    }

    render() {
        const winner = calculateWinner(this.state.board);
        let status;
        if (winner) {
            status = 'Winner: ' + (winner === 'O' ? 'Computer' : 'You');
        } else if (isGameEnd(this.state.board)) {
            status = 'Game is end in a draw';
        } else {
            status = 'Next move: ' + (this.state.xIsNext ? 'You' : 'Computer')
        }
        return (
            <div>
                <div className="status">{status}</div>
                <div className="board-row">
                    {this.renderSquare(0)}
                    {this.renderSquare(1)}
                    {this.renderSquare(2)}
                </div>
                <div className="board-row">
                    {this.renderSquare(3)}
                    {this.renderSquare(4)}
                    {this.renderSquare(5)}
                </div>
                <div className="board-row">
                    {this.renderSquare(6)}
                    {this.renderSquare(7)}
                    {this.renderSquare(8)}
                </div>
                <SockJsClient url='http://localhost:8080/stomp' topics={['/topic/game.event']}
                              onMessage={(msg) => {
                                  this.updateState(msg.board);
                              }}
                              ref={(client) => {
                                  this.clientRef = client
                              }}/>
            </div>
        );
    }
}

class Game extends React.Component {
    render() {
        return (
            <div className="game">
                <div className="game-board">
                    <Board/>
                </div>
                <div className="game-info">
                    <div>{/* status */}</div>
                    <ol>{/* TODO */}</ol>
                </div>
            </div>
        );
    }
}

function isGameEnd(board) {
    for (let i = 0; i < board.length; i++) {
        if (board[i] === null)
            return false;
    }
    return true;
}

function calculateWinner(squares) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6],
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }
    return null;
}


ReactDOM.render(
    <React.StrictMode>
        <Game/>
    </React.StrictMode>,
    document.getElementById('root')
);

