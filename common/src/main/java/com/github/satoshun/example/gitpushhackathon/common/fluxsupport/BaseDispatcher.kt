package com.github.satoshun.example.gitpushhackathon.common.fluxsupport

interface BaseDispatcher<T> :
    BaseSource<T>,
    BaseSink<T>
