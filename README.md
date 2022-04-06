ReMASTER
========

ReMASTER is an in-progress rewrite of the
[MASTER](https://tgvaughan.github.io/MASTER) tree simulation package
for [BEAST 2](https://beast2.org).

It aims to address the following problems with MASTER:
1. slow and memory-intensive tree simulation,
2. awkward coalescent simulation,
3. difficult to configure sampling in BD models,
4. inflexible design.

ReMASTER is in development and is not yet ready for use.

Development News
----------------

### 2022-04-06

Remaster can now simulate birth-death trajectories and birth-death sampling trees with piecewise-constant rate
variation.  There's no support yet for contemporaneous sampling (or birth/death) events, nor is there yet support
for the approximate algorithms needed to handle extremely large populations (10^6+), but in some ways it is already
more flexible and capable than MASTER.  Here is an example SEIR simulation XML with a finite sampling window:

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

Note that "populations" are initialized using regular `RealParameter`s (actually `Function`s), and that all `rate`
and `changeTimes` inputs take `Functions`. This makes it possible to use any other `BEASTObject` implementing `Function`
to specify these.

And yes, `SimulatedTree` is an actual BEAST tree.


License
-------

ReMASTER is free software, and is released under the terms of version
3 of the GNU General Public License, which can be found in this
directory in the file named COPYING.
