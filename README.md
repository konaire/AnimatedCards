# Animated Cards
<img src="assets/example.gif" alt="Example" width="210"/>

## Description
It's a simple master-detail application with interesting animation effects. This app provides examples of terrific animations which you can implement using only standard elements of Android Material Design. All screens were designed using `ConstraintLayout` and based on fragments.

List of cards contains `CoordinatorLayout` with `CollapsingToolbarLayout`. They manage curtain effect when user pulls image from the top. Locations of title, subtitle and fab are calculated by custom `CoordinatorLayout.Behavior`. `OnOffsetChangedListener` manages height of item and its image. It provides smoother animation than `onDependentViewChanged` method from `AppBarLayout.ScrollingViewBehavior`. A shared element transition is used to animate switching of fragments.

Data source was hardcoded but I added a delay using _RxJava_ like in a real network based app. Also enter animation was added after "loading" a data. It is based on a `ConstraintSet`.