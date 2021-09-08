import React from "react"
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Button from "react-bootstrap/Button";
import ShowCard from "./ShowCard";
import ShowHand from "./ShowHand";
import GameNarration from "./gameNarration";
class App extends React.Component {
  // this is used by the consructor to set initial state
  // Notice where initial and buttonText is changed and how they are used
  initialGameState = {
    initial: true,
    buttonText: "Start Game"
  };
  constructor(props) {
    super(props);
    this.state = this.initialGameState;
  }

  // A fixed gameId
  gameId = "Demo";
  // When the game is new (this.state.initial === true), the POST requests a new game.
  // After the first press, PUT is used to get next plays
  // When the game is new (this.state.initial === true), the POST requests a new game.
  // After the first press, PUT is used to get next plays
  startGame = () => {
    fetch("http://localhost:8080/games", {

      method: (this.state.initial ? "POST" : "PUT"),
      // JSON.stringify converts the object into a JSON formatted string
      // Assign that string to the body of the request
      body: JSON.stringify({
        gameId: this.gameId,
      }),
      // header tells the server the content type in body of the text.  (json)
      headers: {
        "Content-Type": "application/json",
      },

    })
      // fetch returns a promise
      // .then attaches callback functions 
      // .then can be chained
      // In this case, the raw data is parsed, and passed to the next handler which updates stae
      .then((data) => {
        let d = data.json(); // data is the raw data data stream from the server
        return d;            // d refers to the parsed data
      })
      .then((game) => {
        // The state is set, which will call render to display 
        // The button stays, but its text changes
        this.setState({
          initial: false,
          game: game,
          gameId: this.gameId,
          buttonText: "Next Turn",
        });
      });

    //this.gameNarration();

  };


  reStartGame = () => {
    fetch("http://localhost:8080/games", {
      method: "DELETE",
      body: JSON.stringify({
        // todo: should be 'event'
        gameId: this.gameId,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((data) => {
        let d = data.json();
        return d;
      })
      .then((game) => {
        this.setState(this.initialGameState);
        this.startGame();
        
      });

  };
  render() {

    let pages = [];
    let playerNames = ["Player 1", "Player 2", "Player 3", "Player 4"];
    if (!this.state.initial) {
      for (let i = 0; i < this.state.game.hands.length; i++) {
        
        pages.push(
          <ShowHand
            key={i}
            initial={this.state.initial}
            hand={
              this.state.initial
                ? {}
                : Object.assign({}, this.state.game.hands[i])
            }
            playerNumber={i}
            playerName={playerNames[i]}
          ></ ShowHand>

        );
        

      }
    }
    return (
      <div className="App">
        <header className="App-header">
          
          <ShowCard
            initial={this.state.initial}
            topDiscard={
              this.state.initial
                ? {}
                : Object.assign({}, this.state.game.cardPlayed ? this.state.game.cardPlayed : this.state.game.topDiscard)
            }
          ></ShowCard>
          {pages}
          <Button variant="dark" onClick={this.startGame}>
            {this.state.buttonText}
          </Button>{" "}
          <br />
          <Button variant="dark" onClick={this.reStartGame}>
            Restart Game
          </Button>{" "}
          <br />
          <GameNarration
            initial={this.state.initial}
            game={
              Object.assign({}, this.state.game)
            }
            
          ></GameNarration>
          
        </header>
      </div>
    );
  }
  
}

export default App;