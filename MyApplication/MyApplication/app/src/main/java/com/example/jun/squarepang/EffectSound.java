package com.example.jun.squarepang;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.SoundPool;

import java.io.FileDescriptor;

/**
 * Created by Jun on 2017-12-08.
 */

public class EffectSound extends SoundPool {
    Context context;
    int blocksound,doublechance,plusscore,timeattack ;

    public EffectSound(int maxStreams, int streamType, int srcQuality,Context context) {
        super(maxStreams, streamType, srcQuality);
        this.context = context;
        blocksound =  this.load(context,R.raw.blockdeletesound,1);
        doublechance = this.load(context,R.raw.doublechancesound,1);
        plusscore = this.load(context,R.raw.plusscoresound,1);
        timeattack = this.load(context,R.raw.timeattacksound,1);
    }
    @Override
    protected void finalize() {
        super.finalize();
    }

    @Override
    public int load(String path, int priority) {
        return super.load(path, priority);
    }

    @Override
    public int load(Context context, int resId, int priority) {
        return super.load(context, resId, priority);
    }

    @Override
    public int load(AssetFileDescriptor afd, int priority) {
        return super.load(afd, priority);
    }

    @Override
    public int load(FileDescriptor fd, long offset, long length, int priority) {
        return super.load(fd, offset, length, priority);
    }

    @Override
    public void setOnLoadCompleteListener(OnLoadCompleteListener listener) {
        super.setOnLoadCompleteListener(listener);
    }


}
