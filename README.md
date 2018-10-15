# RoundRectLayout

对  https://github.com/GcsSloop/rclayout 进行修改，减少方法数和不必要的变量以满足实际需要，这个布局的修改的目的是避免出现以点九图为背景时圆角被覆盖的情况发生，主要代码在RoundRectLayout.java


以此为思路，可以解决cardview在4.4设备出现白边的问题，目前只测试了4.4设备不在出现白边，达到与5.0以上的设备的效果。

![avatar](https://raw.githubusercontent.com/xdhuangsidi/RoundRectLayout/master/app/Screenshot_2018-10-15-17-52-30.jpeg)
