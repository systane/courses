import React, { Component } from 'react';
import { Provider } from 'react-redux';
import store from './store'

import TodoList from './Todolist'

class App extends Component {
  render(){
    return (
      <Provider store={store}>
        <div className="App">
         <p>React with Redux-Saga</p>
         <TodoList/>
      </div>
      </Provider>
    );
  }
}

export default App;
