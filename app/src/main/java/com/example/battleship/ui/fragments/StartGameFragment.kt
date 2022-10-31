package com.example.battleship.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.battleship.R
import com.example.battleship.databinding.FragmentStartGameBinding
import com.example.battleship.model.entities.Orientation
import com.example.battleship.model.entities.ShipType
import com.example.battleship.ui.adapters.MatrixAdapter
import com.example.battleship.viewmodel.GameViewModel


class StartGameFragment : Fragment() {

    lateinit var binding: FragmentStartGameBinding
    private val gameViewModel: GameViewModel by activityViewModels()
    private lateinit var playerMatrixAdapter: MatrixAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val playerMatrix = gameViewModel.playerMatrix.value!!
        binding.playerMatrix.layoutManager =
            GridLayoutManager(requireContext(), playerMatrix.rows())
        playerMatrixAdapter = MatrixAdapter(playerMatrix)
        binding.playerMatrix.adapter = playerMatrixAdapter
        binding.apply {
            buttonShip1.setOnClickListener {
                changeShipType(ShipType.Ship1)
                it.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            buttonShip2Horizontal.setOnClickListener {
                changeShipType(ShipType.Ship2)
                gameViewModel.shipOrientation.value = Orientation.Horizontal
                it.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            buttonShip2Vertical.setOnClickListener {
                changeShipType(ShipType.Ship2)
                gameViewModel.shipOrientation.value = Orientation.Vertical
                it.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            buttonShip3Horizontal.setOnClickListener {
                changeShipType(ShipType.Ship3)
                gameViewModel.shipOrientation.value = Orientation.Horizontal
                it.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            buttonShip3Vertical.setOnClickListener {
                changeShipType(ShipType.Ship3)
                gameViewModel.shipOrientation.value = Orientation.Vertical
                it.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            buttonShip4Horizontal.setOnClickListener {
                changeShipType(ShipType.Ship4)
                gameViewModel.shipOrientation.value = Orientation.Horizontal
                it.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            buttonShip4Vertical.setOnClickListener {
                changeShipType(ShipType.Ship4)
                gameViewModel.shipOrientation.value = Orientation.Vertical
                it.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            createGameBtn.setOnClickListener{
                TODO("create session with repository and move to next fragment")
            }

        }
        playerMatrixAdapter.setOnItemClickListener(object :
            MatrixAdapter.OnItemClickListener {
            override fun onItemClick(i: Int, j: Int) {
                gameViewModel.onClick(i, j)
            }
        })
        setObservables()

    }

    private fun changeShipType(value: ShipType) {
        gameViewModel.shipTypeToInstall.value = value
        loopViews(binding.root)
    }

    private fun loopViews(view: ViewGroup) {
        for (i in 0 until view.childCount) {
            val v = view.getChildAt(i)
            if (v is ImageView) {
                v.setBackgroundColor(resources.getColor(R.color.white))
            } else if (v is ViewGroup) {
                loopViews(v)
            }
        }
    }

    private fun setObservables() {
        gameViewModel.playerMatrix.observe(activity as LifecycleOwner) {
            playerMatrixAdapter.setMatrix(it)
        }
        //TODO(set buttons enabled false)

    }
}