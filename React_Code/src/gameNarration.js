import React, { Component } from "react";


class GameNarration extends Component {
  render() {
    
    let player;
    if (this.props.game.currentPlayer === 0) {
      player = "Player 1";
    } else if (this.props.game.currentPlayer === 1) {
      player = "Player 2";
    } else if (this.props.game.currentPlayer === 2) {
      player = "Player 3";
    } else {
      player = "Player 4";
    }

    
    

    let pages = [];
    if (this.props.initial) {
      pages.push(<p>
        Game has Started
      </p>)
    }
    console.log(this.props.game)
    return (<div>
      <p> {player + " is up to play!"} </p>
      
      
      {pages}
    </div>)
  }

  
}

export default GameNarration;





