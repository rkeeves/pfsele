# primefaces-selenide

Tests against Primefaces generated pages tend to be quite flaky with pure selenium/selenide.
This repo is simply a vehicle for opening a discussion about it.

I know...

Using js is not good, but I'm simply unable to make reliable tests using pure dom manipulation and querying.

Below I tried to list some problems I saw.

## Problems

### Dom changes

Due to ajax, the dom changes rapidly and anything can become stale.
For instance: on datatables, selenide's `$$` throws sometimes, so we have to resort to direct css or xpath selection.
Also, if we want to determine ajax behavior by inspecting some spinner or other ui element it can quickly get detached
from the dom...

The list goes on.

### Monster elements

Primefaces components are quite intricate.

For example: the selectOneMenu looks like a simple select, but it:
- has a parent element which basically wraps the components
- has an element which is the currently selected value
- has a floating panel div with ul li entirely outside of the parent, slapped to the end of the document

Selecting this monster (reliably with 0% flake) means:
- You have to click the label to make the panel visible
- If you dont use js, you have to wait for opacity transition (which for some reason sometimes never settles but remains in the style)
- then query the `$$` collection or direct xpath your way to the li
- click the li
- implicitly wait for the panel to disappear
- check that the label has the value

### Complexity mayhem

People love their dialogs.
So Primefaces made it easy to create dialogs.
Which can be within modal dialogs.
Which in turn can be within modal dialogs.

So basically you have a stack of dialogs, and you should always check for transitions and whether you are on the right one.

### Masked inputs, datepickers etc.

Well, datepickers can be done without js, but it's not reliable.
Even if you do a hide via js, you can still end up with invalid state sometimes.

## Solution

> I don't have a solution yet.

But I want to come up with a non-flaky solution.
Or find someone who came up with such as solution.

Also, no `if`s. I've seen some libraries/home projects, but a lot of them have `if`s in their components.
In my experience `if`s always obliterate the tests.

So currently my goal is not about checkstyle or cleancode, but testing whether primefaces' js api 
could be leveraged in some way to reduce flakyness while still maintaining some form of selenium based approach.
(Yes, cypress exists, and it's cool. But selenium is cool to.)