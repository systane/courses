# **React Lifecycle Methods**

All components in React have a lifecycle where they are built, updated and destroyed and in each lifecycle event we have different methods with different responsibilities. We can classificate these event in 3 categories: **Mounting, Update and Unmount**.


![LifecycleDiagram](https://github.com/systane/courses/blob/master/reactAdvanced/reactLifecycle.jpeg)

The diagram above shows all lifecycle avaliable methods in the current version of react (16.11). So of these methods will removed in version 17 of react, so be carefull. In this article I won't talk about the unsafe methods that will be removed from react.

## **constructor**
 This method is used whenever you need to initialize the state and make bind of methods. This method is called before a component be mounted as you can see in the shown diagram. 

 ```
 constructor(props) {
     super(props); //If you want use props, this must be the first line of every constructor.
     this.state = {count: 0}; //This is the only place where we don't need to use this.setState()
     this.handleClick = this.handleClick.bind(this); //Binding of methods
 }
 ```

## **render**
This is the most used lifecycle method. You need to write a render method for every react class. As its name suggests, the main responsability for this method is to render your component to the UI.

```
class Test extends Component{
    render(){
        return <div> Hello World!</div>
    }
}
```

The render method is called on the mount process (when the component is being create) and in the update events. Render method must be pure functions wih no side-effects, in other words pure functions don't change the state. If you need to do it, you can use other lifecycle methods.


 ## **componentDidMount**


