import App, { Container } from 'next/app';
import { Component } from 'react';
import Page from '../components/Page/Page';

class MyApp extends App {
  render() {
    /*
      By the dafault, the next.js encapsulate your pages inside a <App />
      component, but it's possible override it, just creating an 'pages/_app.js'

      Component has the role to render all the children components,
      in this case can be the <Home/>(index.js) or any other component (like <Sell/>, etc)
    */
    const { Component } = this.props;

    return (
      <Container>
        <Page>
          <Component />
        </Page>
      </Container>
    );
  }
}

export default MyApp;