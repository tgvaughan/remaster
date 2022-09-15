Remaster
===========

Remaster is an in-progress rewrite of the
[MASTER](https://tgvaughan.github.io/MASTER) tree simulation package
for [BEAST 2](https://beast2.org).

It aims to address the following problems with MASTER:
1. slow and memory-intensive tree simulation,
2. awkward coalescent simulation,
3. difficult to configure sampling in BD models,
4. inflexible design.

Remaster is still in development and not suitable for production use.
However, some of these goals have already been addressed and, at
least in the author's opinion, it is in some ways already much nicer
to use than MASTER.  If you'd like to play with it and perhaps try
out some of the examples below (all of which are also contained in
the `examples/` directory of this repository), use the following
package repository to install it via BEAUti's package manager:

https://tgvaughan.github.io/remaster/package.xml

Development News
----------------

### 2022-09-15

Simulation of deterministic ODE equivalents of birth-death models
is now possible, simply by replacing `StochasticTrajectory` with
`DeterministicTrajectory`. All models compatible with `StochasticTrajectory`
are supported, including those containing rate shifts or punctual reactions.

```xml
<beast version="2.0" namespace="beast.core.parameter:beast.core:remaster">
    <run spec="Simulator" nSims="1">
        <simulate spec="DeterministicTrajectory" id="SIRTrajectory"
                  maxTime="20"
                  endsWhen="I&lt;1 &amp;&amp; S&lt;500"
                  loggingGridSize="1001">
            <population spec="RealParameter" id="S" value="1000"/>
            <population spec="RealParameter" id="I" value="1"/>
            <population spec="RealParameter" id="R" value="0"/>
            <samplePopulation spec="RealParameter" id="sample" value="0"/>

            <reaction spec="Reaction" rate="0.01"> S + I -> 2I </reaction>
            <reaction spec="Reaction" rate="0 1" changeTimes="2"> I -> R </reaction>
            <reaction spec="Reaction" rate="0.1"> I -> sample</reaction>

            <reaction spec="PunctualReaction" n="5" times="3"> I -> sample </reaction>
        </simulate>

        <logger spec="Logger" fileName="$(filebase).traj">
            <log idref="SIRTrajectory"/>
        </logger>
    </run>
</beast>
```

The optional `loggingGridSize` attribute specifies how many grid points to use
when saving the simulation output to the log file.

### 2022-08-29

I've added another kind of condition: the "must have" condition.
This allows rejection filtering on the final state of the simulation.

For example, the following will ensure the number of samples lies
between 10 and 20:

```xml
<beast version="2.0" namespace="beast.core.parameter:beast.core:remaster">
    <run spec="Simulator" nSims="1">
        <simulate spec="SimulatedTree" id="tree">
            <trajectory spec="StochasticTrajectory" id="traj"
                        mustHave="sample>10 &amp;&amp; sample<20"
                        maxTime="6">
                <population spec="RealParameter" id="X" value="1 0"/>
                <samplePopulation spec="RealParameter" id="sample" value="0"/>
                
                <reaction spec="Reaction" rate="2"> X -> 2X </reaction>
                <reaction spec="Reaction" rate="2"> X -> 2X </reaction>
                <reaction spec="Reaction" rate="0.1"> X -> sample </reaction>
            </trajectory>
        </simulate>
    </run>
</beast>
```

Note the use of the XML entity `&amp;` instead of `&` in the predicate
expression, due to the limitations of XML.

### 2022-05-23

The reaction parser rules have been subtly modified, such that an
ID-less product is considered the child of
1. the first reactant having the same population type, if one exists, or
2. the first reactant if one exists.

If no parent is identified, the reactant is assumed to be an orphan.

These rules mean that appropriate parent-child relationships can be
specified for more kinds of reactions without recourse to the clumsy
":id" notation.

### 2022-05-23

Preliminary support for end conditions has been added.
In Remaster, these conditions are specified using boolean expressions
represented as strings that are subsequently parsed.

For example, the following produces a birth-death-sampling tree using
the first 10 samples produced by the process.

```xml
<beast version="2.0" namespace="beast.core.parameter:beast.core:remaster">
    <run spec="Simulator" nSims="1">
        <simulate spec="SimulatedTree" id="SIRTree">
            <trajectory spec="StochasticTrajectory" id="SIRTrajectory"
                        endsWhen="sample==10" maxTime="6">
                <population spec="RealParameter" id="X" value="1"/>
                <samplePopulation spec="RealParameter" id="sample" value="0"/>

                <reaction spec="Reaction" rate="2"> X -> 2X </reaction>
                <reaction spec="Reaction" rate="1"> X -> 0 </reaction>
                <reaction spec="Reaction" rate="0.1"> X -> sample </reaction>
            </trajectory>
        </simulate>
        <logger spec="Logger" mode="tree" fileName="$(filebase).trees">
            <log spec="TypedTreeLogger" tree="@SIRTree"/>
        </logger>
    </run>
</beast>
```

The expression string may contain equality/inequality operators (==, !=, <, >, <
=, >=), boolean operators (&&, ||), special functions (sum, min, max), as well as
grouping with parentheses. In all cases, population IDs (such as "sample" in the
example) refer to the current size of the population. Non-scalar populations are
vectors, to which sum(), min() and max() may be applied.

The `maxTime` input specifies a maximum time for the simulation.  When
an `endsWhen` input is specified, the condition must be satisfied even if
the maximum simulation time is reached.  (Otherwise the simulation is repeated.)

With this addition, Remaster can perform all birth-death simulations
that were possible in the original MASTER.

### 2022-05-18

Remaster can now simulate birth-death trajectories and birth-death-sampling
trees with punctual events, including contemporaneous sampling.  Such events
take two forms: n-events, which always result in the reaction firing n times,
and p-events, which apply the reaction to every possible reactant combination
with a probability p.

Here's an example BD simulation XML which produces trees containing exactly
10 samples at time 5, and in which all lineages surviving to time 6 are sampled
with probability 0.2:

```xml
<beast version="2.0" namespace="beast.core.parameter:beast.core:remaster">
    <run spec="Simulator" nSims="100">
        <simulate spec="SimulatedTree" id="SIRTree">
            <trajectory spec="StochasticTrajectory" id="SIRTrajectory">
                <population spec="RealParameter" id="X" value="1"/>
                <samplePopulation spec="RealParameter" id="sample" value="0"/>

                <reaction spec="Reaction" rate="2 0" changeTimes="6"> X -> 2X </reaction>
                <reaction spec="Reaction" rate="1 0" changeTimes="6"> X -> 0 </reaction>
                <reaction spec="PunctualReaction" n="10" times="5"> X -> sample </reaction>
                <reaction spec="PunctualReaction" p="0.2" times="6"> X -> sample </reaction>
            </trajectory>
        </simulate>

        <logger spec="Logger" fileName="$(filebase).traj">
            <log idref="SIRTrajectory"/>
        </logger>

        <logger spec="Logger" mode="tree" fileName="$(filebase).trees">
            <log spec="TypedTreeLogger" tree="@SIRTree"/>
        </logger>

        <logger spec="Logger">
            <log spec="beast.evolution.tree.TreeStatLogger" tree="@SIRTree"/>
        </logger>
    </run>
</beast>
```

The `n`, `p` and `times` inputs are all `Functions`, and can all take
multiple values, allowing you to specify multiple punctual events using
a single reaction.  When `n` or `p` have fewer elements than `times`, earlier
values are "recycled" in the same way that they are in R.

Something to be aware of: remaster currently simulates until all reactions stop
firing and there are no more punctual events to consider. This is why the rates
of the continuous reactions are all set to zero at time 6 in the example above.

### 2022-04-06

Remaster can now simulate birth-death trajectories and birth-death sampling
trees with piecewise-constant rate variation. There's no support yet for
contemporaneous sampling (or birth/death) events, nor is there yet support for
the approximate algorithms needed to handle extremely large populations (10^6+),
but in some ways it is already more flexible and capable than MASTER. Here is an
example SEIR simulation XML with a finite sampling window:

```xml
<beast version="2.0" namespace="beast.core.parameter:beast.core:remaster">
    <run spec="Simulator" nSims="1">
        <simulate spec="SimulatedTree" id="SIRTree">
            <trajectory spec="StochasticTrajectory" id="SIRTrajectory">
                <population spec="RealParameter" id="S" value="10000"/>
                <population spec="RealParameter" id="E" value="0"/>
                <population spec="RealParameter" id="I" value="1"/>
                <population spec="RealParameter" id="R" value="0"/>
                <samplePopulation spec="RealParameter" id="sample" value="0"/>

                <reaction spec="Reaction" rate="0.01"> S + I:a -> I:a + E:a </reaction>
                <reaction spec="Reaction" rate="0.5"> E -> I </reaction>
                <reaction spec="Reaction" rate="2.0 0" changeTimes="5"> I -> R </reaction>
                <reaction spec="Reaction" rate="0 2.0" changeTimes="5"> I -> sample </reaction>
            </trajectory>
        </simulate>

        <logger spec="Logger" fileName="$(filebase).traj">
            <log idref="SIRTrajectory"/>
        </logger>

        <logger spec="Logger" mode="tree" fileName="$(filebase).trees">
            <log spec="TypedTreeLogger" tree="@SIRTree"/>
        </logger>
    </run>
</beast>
```

Note that "populations" are initialized using regular `RealParameter`s
(actually `Function`s), and that all `rate` and `changeTimes` inputs take
`Functions`. This makes it possible to use any other `BEASTObject`
implementing `Function` to specify these.

And yes, `SimulatedTree` is an actual BEAST tree.


License
-------

Remaster is free software, and is released under the terms of version
3 of the GNU General Public License, which can be found in this
directory in the file named COPYING.
